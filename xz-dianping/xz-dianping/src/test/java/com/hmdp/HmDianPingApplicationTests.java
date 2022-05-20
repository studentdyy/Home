package com.hmdp;

import cn.hutool.core.bean.BeanUtil;
import com.hmdp.entity.ShopType;
import com.hmdp.service.IShopTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.*;

import static com.hmdp.utils.RedisConstants.CACHE_SHOP_TYPE_KEY;

@SpringBootTest
class HmDianPingApplicationTests {
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    private IShopTypeService typeService;


    @Test
    public void test(){
        List<ShopType> typeList = typeService.query().orderByAsc("sort").list();
//        for(ShopType shopType : typeList){
//            Map<String, Object> shopTypeMap = BeanUtil.beanToMap(shopType,new HashMap<>(), CopyOptions.create()
//                    .setIgnoreNullValue(true)
//                    .setFieldValueEditor((fieldName,fieldValue) -> fieldValue.toString())
//            );
//            System.out.println(shopTypeMap);
//            String key = CACHE_SHOP_TYPE_KEY + shopTypeMap.get("sort").toString();
//            stringRedisTemplate.opsForHash().putAll(key,shopTypeMap);
//        }
    }

    @Test
    public void test2(){
        ArrayList<String> shopTypes = new ArrayList<>();
        Long size = stringRedisTemplate.opsForHash().size(CACHE_SHOP_TYPE_KEY + "*");

        for (int i = 1; i <= 10; i++) {
            Map<Object, Object> shopType = stringRedisTemplate.opsForHash().entries(CACHE_SHOP_TYPE_KEY + i);
            ShopType shopTypeTemp = BeanUtil.fillBeanWithMap(shopType, new ShopType(), false);
            shopTypes.add(shopTypeTemp.toString());
//            System.out.println(shopTypes);
        }

    }

    @Test
    public void test3(){
        List<ShopType> typeList = typeService
                .query().orderByAsc("sort").list();
        System.out.println(typeList);
    }

    @Test
    public void test4(){
        Long size = stringRedisTemplate.opsForHash().size(CACHE_SHOP_TYPE_KEY);
        System.out.println(size);
    }

    @Test
    public void test5(){
//        List<ShopType> typeList = typeService.query().orderByAsc("sort").list();
//        for(ShopType shopType : typeList){
//            stringRedisTemplate.opsForList().rightPush("cache:shop:type:list",shopType.toString());
//        }
        System.out.println(stringRedisTemplate.opsForList().size("cache:shop:type:list"));
        List<String> range = stringRedisTemplate.opsForList().range("cache:shop:type:list", 0, -1);
        System.out.println(range);
//        System.out.println(stringRedisTemplate.opsForList().index("cache:shop:type:list", 1));
    }

}
