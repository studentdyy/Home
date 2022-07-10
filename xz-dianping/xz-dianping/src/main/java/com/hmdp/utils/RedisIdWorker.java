package com.hmdp.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * 全局唯一id生产策略
 *
 * @author dyyhub
 * @date 2022年05月13日 14:03
 */
@Component
public class RedisIdWorker {
    /**
     * 开始的时间戳
     */
    private static final long BEGIN_TIMESTAMP = 1652400000l;

    private static final int COUNT_BITS = 32;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public long nextId(String keyPrefix){
        //1,生成时间戳
        LocalDateTime now = LocalDateTime.now();
        long nowSecond = now.toEpochSecond(ZoneOffset.UTC);
        long timeStamp = nowSecond - BEGIN_TIMESTAMP;
        //2,生成序列号
        //每一天下的单作为key，用Redis的自增长，方便统计每日订单
        String date = now.format(DateTimeFormatter.ofPattern("yyyy:MM:dd"));
        long count = stringRedisTemplate.opsForValue().increment("increment:" + keyPrefix + ":" + date);
        //3,拼接并返回
        //先让timeStamp往左位移32位然后与count进行 或运算填充
        return timeStamp << COUNT_BITS | count;
    }

//    public static void main(String[] args) {
          //生成开始的时间戳
//        LocalDateTime time = LocalDateTime.of(2022,5,13,0,0,0);
//        long l = time.toEpochSecond(ZoneOffset.UTC);
//        System.out.println(l);
//    }
}
