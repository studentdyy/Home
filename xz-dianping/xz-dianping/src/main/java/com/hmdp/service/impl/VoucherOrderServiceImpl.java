package com.hmdp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.hmdp.dto.Result;
import com.hmdp.entity.VoucherOrder;
import com.hmdp.mapper.VoucherOrderMapper;
import com.hmdp.service.ISeckillVoucherService;
import com.hmdp.service.IVoucherOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.utils.RedisIdWorker;
import com.hmdp.utils.UserHolder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Slf4j
@Service
public class VoucherOrderServiceImpl extends ServiceImpl<VoucherOrderMapper, VoucherOrder> implements IVoucherOrderService {

    @Resource
    private ISeckillVoucherService seckillVoucherService;

    @Resource
    private RedisIdWorker redisIdWorker;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedissonClient redisClient;

    private static final DefaultRedisScript<Long> SECKILL_SCRIPT;
    static {
        SECKILL_SCRIPT = new DefaultRedisScript<>();
        SECKILL_SCRIPT.setLocation(new ClassPathResource("script/seckill.lua"));
        SECKILL_SCRIPT.setResultType(Long.class);
    }
    //线程池
    private static final ExecutorService SECKILL_ORDER_EXECUTOR = Executors.newSingleThreadExecutor();
//    @PostConstruct
//    private void init (){
//        SECKILL_ORDER_EXECUTOR.submit(new VoucherOrderHandler());
//    }

    private class VoucherOrderHandler implements Runnable{

        @SneakyThrows
        @Override
        public void run() {
            String queueName = "stream.orders";
            while(true){
                try {
                    //获取消息队列中的订单信息  XREADGROUP GROUP g1 c1 COUNT 1 BLOCK 2000 STREAMS s1 >
                    List<MapRecord<String, Object, Object>> list = stringRedisTemplate.opsForStream().read(
                            Consumer.from("g1", "c1"),
                            StreamReadOptions.empty().count(1).block(Duration.ofSeconds(20)),
                            StreamOffset.create(queueName, ReadOffset.lastConsumed())
                    );
                    //判断订单消息是否为空
                    if (list == null || list.isEmpty()) {
                        //如果为null，说明没有消息，继续下一次循环
                        continue;
                    }
                    //解析消息得到order
                    MapRecord<String, Object, Object> record = list.get(0);
                    Map<Object, Object> value = record.getValue();
                    VoucherOrder voucherOrder = BeanUtil.fillBeanWithMap(value, new VoucherOrder(), true);
                    //3，创建订单
                    createVoucherOrder(voucherOrder);
                    //4,确认消息，XACK s1 g1 id
                    stringRedisTemplate.opsForStream().acknowledge(queueName,"g1",record.getId());
                } catch (Exception e) {
                    log.error("处理订单异常",e);
                    handlePendingList();
                }
            }
        }

        private void handlePendingList() throws InterruptedException {
            String queueName = "stream.orders";
            while(true){
                try {
                    //获取pendingList中的订单信息  XREADGROUP GROUP g1 c1 COUNT 1 BLOCK 2000 STREAMS s1 0
                    List<MapRecord<String, Object, Object>> list = stringRedisTemplate.opsForStream().read(
                            Consumer.from("g1", "c1"),
                            StreamReadOptions.empty().count(1),
                            StreamOffset.create(queueName, ReadOffset.from("0"))
                    );
                    //判断订单消息是否为空
                    if (list == null || list.isEmpty()) {
                        //如果为null，说明pendList中没有异常消息消息，结束循环
                        break;
                    }
                    //解析消息得到order
                    MapRecord<String, Object, Object> record = list.get(0);
                    Map<Object, Object> value = record.getValue();
                    VoucherOrder voucherOrder = BeanUtil.fillBeanWithMap(value, new VoucherOrder(), true);
                    //3，创建订单
                    createVoucherOrder(voucherOrder);
                    //4,确认消息，XACK s1 g1 id
                    stringRedisTemplate.opsForStream().acknowledge(queueName,"g1",record.getId());
                } catch (Exception e) {
                    log.error("处理订单异常",e);
                    Thread.sleep(1000);
                }
            }
        }
    }



    //阻塞队列
    /*private BlockingQueue<VoucherOrder> orderTasks = new ArrayBlockingQueue<>(1024 * 1024);
    private class VoucherOrderHander implements Runnable{

        @Override
        public void run() {
            while(true){
                try {
                    //获取队列中的订单信息
                    VoucherOrder voucherOrder = orderTasks.take();
                    //2，创建订单
                    createVoucherOrder(voucherOrder);
                } catch (Exception e) {
                    log.error("处理订单异常",e);
                }
            }
        }
    }*/

    private void createVoucherOrder(VoucherOrder voucherOrder) {
        //(5)一人一单
        Long userId = voucherOrder.getUserId();
        Long voucherId = voucherOrder.getVoucherId();

        //创建锁对象
        RLock redisLock = redisClient.getLock("lock:order:" + userId);
        //尝试获取锁
        boolean isLock = redisLock.tryLock();
        //判断
        if(!isLock){
            //获取锁失败可以再次重试，也可以直接返回错误
            log.error("不允许重复下单！");
            return;
        }

        //这里的userId.toString() 底层是调用Long的一个方法，还是会创建一个新的string对象
        //所以我们要丢到常量池里
        try{
            //5.1 查询订单
            Integer count = query().eq("user_id", userId).eq("voucher_id", voucherId).count();
            //5.2 判断是否存在
            if(count > 0){
                log.error("只能购买一次！");
                return;
            }

            //6，扣减库存
            Boolean success = seckillVoucherService.update()
                    .setSql("stock = stock - 1")
                    .eq("voucher_id", voucherId)
                    //用乐观锁的机制解决超卖问题，在修改库存前判断这个库存还是否是之前查出来的库存，如果相同
                    //说明库存数量没被别的线程动过，可以减
                    //.eq("stock",voucher.getStock())
                    //但是这样有个问题就是失败的概率大大提高，我们只需要判断库存大于0即可!
                    .gt("stock",0)
                    .update();
            if (!success) {
                log.error("库存不足！");
                return;
            }
            save(voucherOrder);
        }finally {
            //释放锁
            redisLock.unlock();
        }
    }
    @Override
    public Result seckillVoucher(Long voucherId) {
        Long userId = UserHolder.getUser().getId();
        long orderId = redisIdWorker.nextId("order");
        //1,执行lua脚本
        Long result = stringRedisTemplate.execute(SECKILL_SCRIPT,
                Collections.emptyList(),
                voucherId.toString(),
                userId.toString(),
                String.valueOf(orderId));

        int r = result.intValue();
        //2，判断结果是否为0
        if (r != 0){
            //2.1不为0，代表没有购买资格
            return Result.fail(r == 1 ? "库存不足":"不能重复下单");
        }
        //返回订单id
        return Result.ok(orderId);

    }




    /*
    自动手动塞到阻塞队列
    @Override
    public Result seckillVoucher(Long voucherId) {
        Long userId = UserHolder.getUser().getId();
        //1,执行lua脚本
        Long result = stringRedisTemplate.execute(SECKILL_SCRIPT,
                Collections.emptyList(),
                voucherId.toString(),
                userId.toString());

        int r = result.intValue();
        //2，判断结果是否为0
        if (r != 0){
            //2.1不为0，代表没有购买资格
            return Result.fail(r == 1 ? "库存不足":"不能重复下单");
        }
        //2.2为0则有购买资格,将下单信息保存到阻塞队列中
        //3，创建订单
        VoucherOrder voucherOrder = new VoucherOrder();
        long orderId = redisIdWorker.nextId("order");
        voucherOrder.setId(orderId);
        voucherOrder.setUserId(userId);
        voucherOrder.setVoucherId(voucherId);
        //3.1放入阻塞队列中
        orderTasks.add(voucherOrder);

        //返回订单id
        return Result.ok(orderId);

    }*/


    /*@Override
    public Result seckillVoucher(Long voucherId) {
        //1.查询优惠卷
        SeckillVoucher voucher = seckillVoucherService.getById(voucherId);
        //2，判断秒杀是否开始
        if (voucher.getBeginTime().isAfter(LocalDateTime.now())) {
            //尚未开始
            return Result.fail("秒杀活动尚未开始!");
        }
        //3，判断秒杀是否结束
        if (voucher.getEndTime().isBefore(LocalDateTime.now())) {
            //尚未开始
            return Result.fail("秒杀活动已经结束!");
        }
        //4，判断库存是否充足
        if (voucher.getStock() < 1) {
            //库存不足
            return Result.fail("库存不足！");
        }
        //5，创建订单
        return createVoucherOrder(voucherId);
    }*/



    /**
     * 使用redisson代替自定义锁
     @Transactional
     public Result createVoucherOrder(Long voucherId) {
         //(5)一人一单
         Long userId = UserHolder.getUser().getId();

         //创建锁对象
         RLock redisLock = redisClient.getLock("lock:order:" + userId);
         //尝试获取锁
         boolean isLock = redisLock.tryLock();
         //判断
         if(!isLock){
         //获取锁失败可以再次重试，也可以直接返回错误
            return Result.fail("不允许重复下单");
         }

         //这里的userId.toString() 底层是调用Long的一个方法，还是会创建一个新的string对象
         //所以我们要丢到常量池里
         try{
             //5.1 查询订单
             Integer count = query().eq("user_id", userId).eq("voucher_id", voucherId).count();
         //5.2 判断是否存在
         if(count > 0){
            return  Result.fail("只能购买一次！");
         }

         //6，扣减库存
         Boolean success = seckillVoucherService.update()
         .setSql("stock = stock - 1")
         .eq("voucher_id", voucherId)
         //用乐观锁的机制解决超卖问题，在修改库存前判断这个库存还是否是之前查出来的库存，如果相同
         //说明库存数量没被别的线程动过，可以减
         //.eq("stock",voucher.getStock())
         //但是这样有个问题就是失败的概率大大提高，我们只需要判断库存大于0即可!
         .gt("stock",0)
         .update();
         if (!success) {
            return Result.fail("库存不足！");
         }

         //7，创建订单
         VoucherOrder voucherOrder = new VoucherOrder();
         long orderId = redisIdWorker.nextId("order");
         voucherOrder.setId(orderId);
         voucherOrder.setUserId(userId);
         voucherOrder.setVoucherId(voucherId);

         save(voucherOrder);
         return Result.ok(orderId);
         }finally {
             //释放锁
             redisLock.unlock();
         }
     }
     */



    /**
     * 使用自定义的锁
    @Transactional
    public Result createVoucherOrder(Long voucherId) {
        //(5)一人一单
        Long userId = UserHolder.getUser().getId();

        //创建锁对象
        SimpleRedisLock redisLock = new SimpleRedisLock("order:" + userId,stringRedisTemplate);
        //尝试获取锁
        boolean isLock = redisLock.tryLock(1200);
        //判断
        if(!isLock){
            //获取锁失败可以再次重试，也可以直接返回错误
            return Result.fail("不允许重复下单");
        }

        //这里的userId.toString() 底层是调用Long的一个方法，还是会创建一个新的string对象
        //所以我们要丢到常量池里
        try{
            //5.1 查询订单
            Integer count = query().eq("user_id", userId).eq("voucher_id", voucherId).count();
            //5.2 判断是否存在
            if(count > 0){
                return  Result.fail("只能购买一次！");
            }

            //6，扣减库存
            Boolean success = seckillVoucherService.update()
                    .setSql("stock = stock - 1")
                    .eq("voucher_id", voucherId)
                    //用乐观锁的机制解决超卖问题，在修改库存前判断这个库存还是否是之前查出来的库存，如果相同
                    //说明库存数量没被别的线程动过，可以减
                    //.eq("stock",voucher.getStock())
                    //但是这样有个问题就是失败的概率大大提高，我们只需要判断库存大于0即可!
                    .gt("stock",0)
                    .update();
            if (!success) {
                return Result.fail("库存不足！");
            }

            //7，创建订单
            VoucherOrder voucherOrder = new VoucherOrder();
            long orderId = redisIdWorker.nextId("order");
            voucherOrder.setId(orderId);
            voucherOrder.setUserId(userId);
            voucherOrder.setVoucherId(voucherId);

            save(voucherOrder);
            return Result.ok(orderId);
        }finally {
            //释放锁
            redisLock.unlock();
        }
    }
     */
/*     旧版的一人一单的代码，仅适用与单个服务器
    @Transactional
    public Result createVoucherOrder(Long voucherId) {
        //(5)一人一单
        Long userId = UserHolder.getUser().getId();
        //这里的userId.toString() 底层是调用Long的一个方法，还是会创建一个新的string对象
        //所以我们要丢到常量池里
        synchronized (userId.toString().intern()){
            //5.1 查询订单
            Integer count = query().eq("user_id", userId).eq("voucher_id", voucherId).count();
            //5.2 判断是否存在
            if(count > 0){
                return  Result.fail("只能购买一次！");
            }

            //6，扣减库存
            Boolean success = seckillVoucherService.update()
                    .setSql("stock = stock - 1")
                    .eq("voucher_id", voucherId)
                    //用乐观锁的机制解决超卖问题，在修改库存前判断这个库存还是否是之前查出来的库存，如果相同
                    //说明库存数量没被别的线程动过，可以减
                    //.eq("stock",voucher.getStock())
                    //但是这样有个问题就是失败的概率大大提高，我们只需要判断库存大于0即可!
                    .gt("stock",0)
                    .update();
            if (!success) {
                return Result.fail("库存不足！");
            }

            //7，创建订单
            VoucherOrder voucherOrder = new VoucherOrder();
            long orderId = redisIdWorker.nextId("order");
            voucherOrder.setId(orderId);
            voucherOrder.setUserId(userId);
            voucherOrder.setVoucherId(voucherId);

            save(voucherOrder);
            return Result.ok(orderId);
        }
    }*/
}
