package com.dyyhub.base.multithreaded_.threadsafetyissues;

import com.dyyhub.base.multithreaded_.utils.ThreadUtils;

/**
 * @author dyyhub
 * @date 2022年06月26日 14:35
 * 死锁演示
 * 死锁产生的4个必要条件
 * 1、互斥使用，即当资源被一个线程使用(占有)时，别的线程不能使用
 *
 * 2、不可抢占，资源请求者不能强制从资源占有者手中夺取资源，资源只能由资源占有者主动释放。
 *
 * 3、请求和保持，即当资源请求者在请求其他资源的同时保持对原有资源的占有。
 *
 * 4、循环等待，即存在一个等待队列：P1占有P2的资源，P2占有P3的资源，P3占有P1的资源。这样就形成了一个等待环路
 */
public class Deadlock_ {
    private static Object MONITOR1 = new Object();
    private static Object MONITOR2 = new Object();

    public static void main(String[] args) {
        new Thread(()->{
            synchronized (MONITOR1){
                System.out.println(Thread.currentThread().getName() + "获取了1号锁");
                ThreadUtils.sleep(200);
                synchronized (MONITOR2){
                    System.out.println(Thread.currentThread().getName() + "获取了2号锁");
                }
            }
        },"thread1").start();

        new Thread(()->{
            synchronized (MONITOR2){
                System.out.println(Thread.currentThread().getName() + "获取了2号锁");
                ThreadUtils.sleep(200);
                synchronized (MONITOR1){
                    System.out.println(Thread.currentThread().getName() + "获取了1号锁");
                }
            }
        },"thread2").start();

    }
}
