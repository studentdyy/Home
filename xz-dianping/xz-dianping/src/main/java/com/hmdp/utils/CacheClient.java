package com.hmdp.utils;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hmdp.entity.Shop;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static com.hmdp.utils.RedisConstants.*;

/**
 * Redis工具类
 *
 * @author dyyhub
 * @date 2022年05月12日 20:17
 */
@Slf4j
@Component
public class CacheClient {

    private static final ExecutorService CACHE_REBUILD_EXECUTOR = Executors.newFixedThreadPool(10);


    private final StringRedisTemplate stringRedisTemplate;

    public CacheClient(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 写入Redis缓存
     *
     * @param key   缓存前缀
     * @param value 缓存对象
     * @param time  缓存过期时间
     * @param timeUnit  缓存过期时间单位
     */
    public void set(String key, Object value, Long time, TimeUnit timeUnit){
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(value),time,timeUnit);
    }

    /**
     * 设置逻辑过期时间
     *
     * @param key   存入Redis的key
     * @param value 存入Redis的value
     * @param time  逻辑过期时间
     * @param timeUnit  逻辑过期时间单位
     */
    public void setWithLogicExpire(String key, Object value, Long time, TimeUnit timeUnit){
        RedisData redisData = new RedisData();
        redisData.setData(value);
        redisData.setExpireTime(LocalDateTime.now().plusSeconds(timeUnit.toSeconds(time)));

        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(redisData));
    }

    /**
     * 解决缓存穿透工具类
     *
     * @param keyPrefix 存入Redis的Key前缀
     * @param id    id
     * @param type  id返回的类型
     * @param dbFallback    用什么方法从数据库返回对应类型数据
     * @param time  缓存过期时间
     * @param timeUnit  缓存过期时间单位
     * @param <R>   类类型
     * @param <ID>  id
     */
    public <R,ID> R queryWithPassThrough(String keyPrefix, ID id, Class<R> type, Function<ID,R> dbFallback,Long time, TimeUnit timeUnit){
        String key = keyPrefix + id;
        //1，先去缓存那么找数据
        String json = stringRedisTemplate.opsForValue().get(key);
        //2，判断是否存在
        if (StrUtil.isNotBlank(json)) {
            //3，存在直接返回
            return JSONUtil.toBean(json, type);
        }
        //解决缓存穿透问题，判断命中的是否为空值。
        if(json != null){
            //此时，shopJson一定为空字符串，返回一个错误信息
            return null;
        }

        //4，不存在，就根据id查找数据库
        R r = dbFallback.apply(id);
        //5，判断r的值是否为空
        if(r == null){
            //解决缓存穿透问题，若数据库查询为空，则缓存空值，设置期限为2分钟
            stringRedisTemplate.opsForValue().set(key,"",CACHE_NULL_TTL, TimeUnit.MINUTES);
            return null;
        }
        //6，r的值不为空就写入Redis
        this.set(key,r,time,timeUnit);

        return r;
    }

    /**
     * 解决缓存击穿工具类
     *
     * @param keyPrefix
     * @param id
     * @param type
     * @param dbFallback
     * @param lockKeyPrefix
     * @param time
     * @param timeUnit
     * @param <R>
     * @param <ID>
     * @return
     */
    public <R,ID> R queryWithLogicExpire(
            String keyPrefix,ID id,Class<R> type,Function<ID,R> dbFallback,String lockKeyPrefix,Long time, TimeUnit timeUnit){
        String key = keyPrefix + id;
        //1，先去缓存那么找数据
        String Json = stringRedisTemplate.opsForValue().get(key);
        //2，如果有值，直接返回
        if (StrUtil.isBlank(Json)) {
            //3.未命中 直接返回
            return null;
        }

        //4,命中，需要先把json反序列化为对象
        RedisData redisData = JSONUtil.toBean(Json,RedisData.class);
        R r = JSONUtil.toBean((JSONObject)redisData.getData(), type);
        LocalDateTime expireTime = redisData.getExpireTime();

        //5,判断是否过期
        if(expireTime.isAfter(LocalDateTime.now())){
            //5.1，未过期，则直接返回店铺信息
            return r;
        }
        //5.2，已过期，需要进行缓存重建

        //6，缓存重建
        //6.1 获取互斥锁
        String lockKey = lockKeyPrefix + id;
        boolean isLock = tryToGetKey(lockKey);
        //6.2 判断是否获取锁成功
        if(isLock){
            //6.3 成功，创建新的线程去查找数据库，然后更新缓存，然后释放锁
            //用线程池去做，不然经常创建销毁影响性能
            CACHE_REBUILD_EXECUTOR.submit(()->{
                try {
                    //查询数据库
                    R r1 = dbFallback.apply(id);
                    //写入缓存，设置逻辑过期时间
                    this.setWithLogicExpire(key,r1,time,timeUnit);

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }finally {
                    unlock(lockKey);
                }
            });
        }
        //6.4 失败,则返回过期的信息
        return r;
    }


    private boolean tryToGetKey(String key){
        Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(key, "1", 10, TimeUnit.SECONDS);
        //不能直接返回，以免出现空指针
        return BooleanUtil.isTrue(flag);
    }

    private void unlock(String key){
        stringRedisTemplate.delete(key);
    }

}
