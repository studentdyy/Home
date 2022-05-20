package com.hmdp.uv;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 * @author dyyhub
 * @date 2022年05月18日 16:23
 */
@SpringBootTest
public class UvTest {
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Test
    void TestHyperLogLog() {
        //模拟一百万个用户的uv操作
        String[] values = new String[1000];
        int j = 0;
        for (int i = 0; i < 1000000; i++) {
            j = i % 1000;
            values[j] = "user_" + i;
            if(j == 999){
                stringRedisTemplate.opsForHyperLogLog().add("hl1",values);
            }
        }
        //统计
        Long count = stringRedisTemplate.opsForHyperLogLog().size("hl1");
        System.out.println("count = " + count);
    }

}
