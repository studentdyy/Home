package com.hmdp.config;

import com.hmdp.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author dyyhub
 * @date 2022年05月11日 15:33
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {


        @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor(stringRedisTemplate)).excludePathPatterns(
                "/user/code",
                "/user/login",
                "/shop-type/**",
                "/voucher/**",
                "/upload/**",
                "/blog/hot",
                "/shop/**"
        );
    }
}
