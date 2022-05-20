package com.hmdp.service;

import com.hmdp.entity.Shop;
import com.hmdp.service.impl.ShopServiceImpl;
import com.hmdp.utils.CacheClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

import java.util.concurrent.TimeUnit;

import static com.hmdp.utils.RedisConstants.CACHE_SHOP_KEY;

/**
 * @author dyyhub
 * @date 2022年05月12日 19:37
 */
@SpringBootTest
public class ShopServiceImplTest {
    @Resource
    private CacheClient cacheClient;

    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Resource
    ShopServiceImpl shopService;
    @Test
    public void test1() throws InterruptedException {
        shopService.saveShop2Redis(1l,10l);
    }

    @Test
    public void test2(){
        Shop shop = shopService.getById(1l);
        cacheClient.setWithLogicExpire(CACHE_SHOP_KEY+1L,shop,10l, TimeUnit.SECONDS);
    }

}
