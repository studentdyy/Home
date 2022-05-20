package com.hmdp.utils.lock;

import cn.hutool.core.lang.UUID;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @author dyyhub
 * @date 2022年05月14日 10:55
 */
public class SimpleRedisLock implements ILock{

    private String lockName;

    private StringRedisTemplate stringRedisTemplate;

    private static final String KEY_PREFIX = "lock:";

    //集群模式下，每个jvm的线程id是递增的难免会引发冲突，因此我们要使用uuid和线程id拼接的方式
    private static final String ID_PREFIX = UUID.randomUUID().toString(true) + "-";
    private static final DefaultRedisScript<Long> UNLOCK_SCRIPT;
    static {
        UNLOCK_SCRIPT = new DefaultRedisScript<>();
        UNLOCK_SCRIPT.setLocation(new ClassPathResource("script/unlock.lua"));
        UNLOCK_SCRIPT.setResultType(Long.class);
    }

    public SimpleRedisLock(String lockName, StringRedisTemplate stringRedisTemplate) {
        this.lockName = lockName;
        this.stringRedisTemplate = stringRedisTemplate;
    }




    @Override
    public boolean tryLock(long timeoutSec) {

//        long currThreadId = Thread.currentThread().getId();
        String currThreadId = ID_PREFIX + Thread.currentThread().getId();

        Boolean result = stringRedisTemplate.opsForValue()
                .setIfAbsent(KEY_PREFIX + lockName, currThreadId, timeoutSec, TimeUnit.SECONDS);
        //不能直接返回result，因为这里有一个自动拆箱的操作，有可能会导致空指针。
        return Boolean.TRUE.equals(result);
    }

    @Override
    public void unlock() {
        stringRedisTemplate.execute(UNLOCK_SCRIPT,
                Collections.singletonList(KEY_PREFIX + lockName),
                ID_PREFIX + Thread.currentThread().getId());
    }


//    @Override
//    public void unlock() {
//        //获取线程标识
//        String currThreadId = ID_PREFIX + Thread.currentThread().getId();
//        //获取锁标识
//        String id = stringRedisTemplate.opsForValue().get(KEY_PREFIX + lockName);
//        //判断标识是否一致
//        if (currThreadId.equals(id)) {
//            //释放锁
//            stringRedisTemplate.delete(KEY_PREFIX + lockName);
//        }
//    }
}
