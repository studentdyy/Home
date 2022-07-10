package com.dyyhub.base.multithreaded_.threadsafetyissues;

import com.dyyhub.base.multithreaded_.utils.ThreadUtils;

import java.util.concurrent.locks.LockSupport;

/**
 * @author dyyhub
 * @date 2022年06月26日 22:03
 * LockSupport工具类的使用demo
 */
public class LockSupportTest {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println(1);
            ThreadUtils.sleep(2000);
            System.out.println(2);
            LockSupport.park();
            System.out.println(3);
        });

        thread.start();
        ThreadUtils.sleep(5000);
        System.out.println(4);
        LockSupport.unpark(thread);
        System.out.println(5);
    }
}
