package com.dyyhub.base.multithreaded_.threadsafetyissues;

import com.dyyhub.demo.T;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dyyhub
 * @date 2022年06月26日 21:29
 * Thread的两个静态方法
 * sleep释放CPU资源，但不释放锁。
 * yield方法释放了CPU的执行权，但是依然保留了CPU的执行资格。这个方法不常用
 */
public class YieldTest {
    private static AtomicInteger COUNT1 = new AtomicInteger();
    private static AtomicInteger COUNT2 = new AtomicInteger();


    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                Thread.yield();
                COUNT1.getAndAdd(1);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                COUNT2.getAndAdd(1);
            }
        });
        thread1.start();
        thread2.start();

        thread2.join();

        System.out.println("thread1执行了：" + COUNT1.get());
        System.out.println("thread2执行了：" + COUNT2.get());


    }
}
