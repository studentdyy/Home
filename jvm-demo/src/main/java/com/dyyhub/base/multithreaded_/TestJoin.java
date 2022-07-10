package com.dyyhub.base.multithreaded_;

import com.dyyhub.base.multithreaded_.utils.ThreadUtils;

/**
 * @author dyyhub
 * @date 2022年06月25日 15:23
 * join方法的运用
 * 那个主线程的子线程调用了join就阻塞主线程
 */
public class TestJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 =  new Thread(()->{
            ThreadUtils.sleep(2000);
            System.out.println("thread1.....");
        });

        Thread thread2 =  new Thread(()->{
            ThreadUtils.sleep(3000);
            System.out.println("thread2.....");
        });
        thread1.start();
        thread2.start();

        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main...");
    }
}
