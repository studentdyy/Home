package com.dyyhub.base.multithreaded_.threadsafetyissues;

import com.dyyhub.base.multithreaded_.utils.ThreadUtils;

/**
 * @author dyyhub
 * @date 2022年06月26日 14:48
 * wait和notify方法的使用
 * 注意1：MONITOR1等待和唤醒必须在持有这个锁的线程当中
 */
public class WaitAndNotify {
    private static Object MONITOR1 = new Object();

    public static void main(String[] args) {
        new Thread(()->{
            synchronized (MONITOR1){
                System.out.println(Thread.currentThread().getName() + "获取了1号锁");
                try {
                    //会释放锁，释放cpu的资源
                    //sleep不会释放锁
                    MONITOR1.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "结束了");
        },"thread1").start();

        new Thread(() -> {
            synchronized (MONITOR1){
                System.out.println(Thread.currentThread().getName() + "获取了1号锁");
                ThreadUtils.sleep(2000);
                //唤醒wait的线程
                //notifyAll()，唤醒所有等待中的线程
                MONITOR1.notify();
            }
            System.out.println(Thread.currentThread().getName() + "结束了");
        },"thread2").start();
    }
}
