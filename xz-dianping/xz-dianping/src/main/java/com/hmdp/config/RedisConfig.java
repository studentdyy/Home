package com.hmdp.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dyyhub
 * @date 2022年05月14日 18:22
 */
@Configuration
public class RedisConfig {
    @Bean
    public RedissonClient redissonClient(){
        //配置类
        Config config = new Config();
        //添加Redis地址,这里是单点的地址，集群可以用config.useClusterServers()添加集群地址
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        //创建客户端
        return Redisson.create(config);
    }
}
