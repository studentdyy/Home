package com.hmdp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.hmdp.dto.Result;
import com.hmdp.entity.ShopType;
import com.hmdp.mapper.ShopTypeMapper;
import com.hmdp.service.IShopTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.hmdp.utils.RedisConstants.CACHE_SHOP_TYPE_KEY;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopTypeServiceImpl extends ServiceImpl<ShopTypeMapper, ShopType> implements IShopTypeService {
    @Resource
    private IShopTypeService typeService;
    @Resource
    StringRedisTemplate stringRedisTemplate;




    @Override
    public Result getAll() {

        //先从缓存拿数据
        List<String> shopTypeList = stringRedisTemplate.opsForList().range(CACHE_SHOP_TYPE_KEY, 0, -1);

        //如果不为空就返回
        if(!shopTypeList.isEmpty()){
            List<ShopType> shopTypes = new ArrayList<>();
            for(String shopType : shopTypeList ){
                ShopType shop = JSONUtil.toBean(shopType, ShopType.class, false);
                shopTypes.add(shop);
            }
            return Result.ok(shopTypes);
        }
        //如果缓存没有就直接查数据库
        List<ShopType> shopTypes = typeService.query().orderByAsc("sort").list();
        //查出来了之后,如果不为空那么就写进缓存
        if(!shopTypes.isEmpty()){
            for(ShopType shopType : shopTypes){
                stringRedisTemplate.opsForList().rightPush(CACHE_SHOP_TYPE_KEY,JSONUtil.toJsonStr(shopType));
            }
            return Result.ok(shopTypes);
        }
        return Result.fail("数据为空");

    }
}
