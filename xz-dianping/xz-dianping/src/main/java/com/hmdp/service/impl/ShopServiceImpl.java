package com.hmdp.service.impl;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hmdp.dto.Result;
import com.hmdp.entity.Shop;
import com.hmdp.mapper.ShopMapper;
import com.hmdp.service.IShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.utils.CacheClient;
import com.hmdp.utils.RedisData;
import com.hmdp.utils.SystemConstants;
import io.lettuce.core.api.sync.RedisCommands;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.domain.geo.GeoReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.hmdp.utils.RedisConstants.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    private CacheClient cacheClient;
    @Override
    public Result queryById(Long id) {
        //缓存穿透解决方案一：缓存空对象
        Shop shop = cacheClient.queryWithPassThrough(CACHE_SHOP_KEY,id,Shop.class,this::getById,CACHE_SHOP_TTL,TimeUnit.MINUTES);

        //缓存击穿解决方案一：互斥锁
        //Shop shop = queryWithMutex(id);

        //缓存击穿解决方案二：逻辑过期
//        Shop shop = cacheClient.queryWithLogicExpire(
//                CACHE_SHOP_KEY,id,Shop.class,this::getById,LOCK_SHOP_KEY,10l,TimeUnit.SECONDS);
        if (shop == null){
            return Result.fail("店铺不存在!");
        }

        return Result.ok(shop);
    }

    private static final ExecutorService CACHE_REBUILD_EXECUTOR = Executors.newFixedThreadPool(10);

    /*
    *缓存击穿之逻辑过期
    * */
    public Shop queryWithLogicExpire(Long id){
        String key = CACHE_SHOP_KEY + id;
        //1，先去缓存那么找数据
        String shopJson = stringRedisTemplate.opsForValue().get(key);
        //2，如果有值，直接返回
        if (StrUtil.isBlank(shopJson)) {
            //3，存在，直接返回
            return null;
        }

        //4,命中，需要先把json反序列化为对象
        RedisData redisData = JSONUtil.toBean(shopJson,RedisData.class);
        Shop shop = JSONUtil.toBean((JSONObject)redisData.getData(), Shop.class);
        LocalDateTime expireTime = redisData.getExpireTime();

        //5,判断是否过期
        if(expireTime.isAfter(LocalDateTime.now())){
            //5.1，未过期，则直接返回店铺信息
            return shop;
        }
        //5.2，已过期，需要进行缓存重建

        //6，缓存重建
        //6.1 获取互斥锁
        String lockKey = LOCK_SHOP_KEY + id;
        boolean isLock = tryToGetKey(lockKey);
        //6.2 判断是否获取锁成功
        if(isLock){
            //6.3 成功，创建新的线程去查找数据库，然后更新缓存，然后释放锁
            //用线程池去做，不然经常创建销毁影响性能
            CACHE_REBUILD_EXECUTOR.submit(()->{
                try {
                    this.saveShop2Redis(id,20l);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }finally {
                    unlock(lockKey);
                }
            });
        }
        //6.4 失败,则返回过期的信息
        return shop;
    }

    /*
     * 缓存击穿之互斥锁
     * */
    public Shop queryWithMutex(Long id){
        String key = CACHE_SHOP_KEY + id;
        //1，先去缓存那么找数据
        String shopJson = stringRedisTemplate.opsForValue().get(key);
        //2，如果有值，直接返回
        if (StrUtil.isNotBlank(shopJson)) {
            return JSONUtil.toBean(shopJson,Shop.class);
        }
        //3，解决缓存穿透问题，判断命中的是否为空值。
        if(shopJson != null){
            //此时，shopJson一定为空字符串，返回一个错误信息
            return null;
        }
        //未命中
        //4，实现缓存重建
        //4.1 获取互斥锁
        String lockKey = LOCK_SHOP_KEY + id;
        boolean isLock = tryToGetKey(lockKey);
        Shop shop = null;
        try{
            //4.2判断是否成功

            if (!isLock) {
                //4.3失败则休眠一段时间继续获取互斥锁，进行递归
                Thread.sleep(50);
                return queryWithMutex(id);
            }

            //4.4成功，就根据id查找数据库
            shop = getById(id);
            //模拟一个延迟
            Thread.sleep(200);
            //5，不存在，返回错误
            if(shop == null){
                //解决缓存穿透问题，若数据库查询为空，则缓存空值，设置期限为2分钟
                stringRedisTemplate.opsForValue().set(key,"",CACHE_NULL_TTL, TimeUnit.MINUTES);
                return null;
            }
            //6，存在，写入redis
            stringRedisTemplate.opsForValue().set(key,JSONUtil.toJsonStr(shop),CACHE_SHOP_TTL, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            //2.释放互斥锁
            unlock(lockKey);
        }

        return shop;
    }



    /*
    * 缓存穿透
    * */
    public Shop queryWithPassThrough(Long id){
        String key = CACHE_SHOP_KEY + id;
        //1，先去缓存那么找数据
        String shopJson = stringRedisTemplate.opsForValue().get(key);
        //2，如果有值，直接返回
        if (StrUtil.isNotBlank(shopJson)) {
            return JSONUtil.toBean(shopJson,Shop.class);
        }
        //3，如果没有值，那么就两种情况，一是null，而是空字符串""
        //解决缓存穿透问题，判断命中的是否为空值。
        if(shopJson != null){
            //此时，shopJson一定为空字符串，返回一个错误信息
            return null;
        }

        //4，未命中 就根据id查找数据库
        Shop shop = getById(id);
        if(shop == null){
            //解决缓存穿透问题，若数据库查询为空，则缓存空值，设置期限为2分钟
            stringRedisTemplate.opsForValue().set(key,"",CACHE_NULL_TTL, TimeUnit.MINUTES);
            return null;
        }
        stringRedisTemplate.opsForValue().set(key,JSONUtil.toJsonStr(shop),CACHE_SHOP_TTL, TimeUnit.MINUTES);
        return shop;
    }


    private boolean tryToGetKey(String key){
        Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(key, "1", 10, TimeUnit.SECONDS);
        //不能直接返回，以免出现空指针
        return BooleanUtil.isTrue(flag);
    }

    private void unlock(String key){
        stringRedisTemplate.delete(key);
    }

    /**
     *  预缓存热点数据
     *
     * @param id    店铺id
     * @param expireSeconds 过期时间的秒数
     * @throws InterruptedException
     */
    public void saveShop2Redis(Long id,Long expireSeconds) throws InterruptedException {
        //1,查询店铺信息
        Shop shop = getById(id);
        Thread.sleep(200);
        //2，封装逻辑过期
        RedisData redisData = new RedisData();
        redisData.setData(shop);
        redisData.setExpireTime(LocalDateTime.now().plusSeconds(expireSeconds));
        //3，写入redis
        stringRedisTemplate.opsForValue().set(CACHE_SHOP_KEY + id,JSONUtil.toJsonStr(redisData));
    }


    /**
     * 更新商铺信息
     * @param shop
     * @return
     */
    @Override
    @Transactional
    public Result update(Shop shop) {
        Long id = shop.getId();
        if(id == null){
            return Result.fail("店铺id不能为空！");
        }
        //先更新数据库
        updateById(shop);
        //在删除缓存
        stringRedisTemplate.delete(CACHE_SHOP_KEY + id);
        return Result.ok();
    }

    @Override
    public Result queryByType(Integer typeId, Integer current, Double x, Double y) {
        //判斷是否根據坐標查詢
        if(x == null || y == null){
            // 根据类型分页查询
            Page<Shop> page = query()
                    .eq("type_id", typeId)
                    .page(new Page<>(current, SystemConstants.DEFAULT_PAGE_SIZE));
            // 返回数据
            return Result.ok(page.getRecords());
        }
        //2,计算分页参数
        int form = (current - 1) * SystemConstants.MAX_PAGE_SIZE;
        int end = current * SystemConstants.MAX_PAGE_SIZE;
        //3.查询redis，按照距离排序，分页，结果：shopId、distance
        String key = SHOP_GEO_KEY + typeId;
        GeoResults<RedisGeoCommands.GeoLocation<String>> results = stringRedisTemplate.opsForGeo() //GEOSEARCH key BYLONLAT x y BYRADIUS 10 WITHDISTANCE
                .search(
                        key,
                        GeoReference.fromCoordinate(x, y),
                        new Distance(5000),
                        RedisGeoCommands.GeoSearchCommandArgs.newGeoSearchArgs().includeCoordinates().limit(end)
                );
        //4,解析出id
        if(results == null){
            //没有结果就返回空
            return Result.ok(Collections.emptyList());
        }

        //通过截取的方式来做分页
        List<GeoResult<RedisGeoCommands.GeoLocation<String>>> content = results.getContent();
        if (content.size() <= form){
            //没有下一页了
            return Result.ok(Collections.emptyList());
        }

        List<Long> ids = new ArrayList<>(content.size());
        Map<String,Distance> distanceMap = new HashMap<>(content.size());
        content.stream().skip(form).forEach(result -> {
            //4.2获取店铺id
            String shopIdStr = result.getContent().getName();
            ids.add(Long.valueOf(shopIdStr));
            //4.3获取距离
            Distance distance = result.getDistance();
            distanceMap.put(shopIdStr,distance);
        });
        //5,根据id查询shop
        String idStr = StrUtil.join(",", ids);
        List<Shop> shops = query().in("id", ids).last("ORDER BY FIELD(" + idStr + ")").list();
        for (Shop shop : shops) {

            shop.setDistance(distanceMap.get(shop.getId().toString()).getValue());
        }

        return Result.ok(shops);
    }

}
