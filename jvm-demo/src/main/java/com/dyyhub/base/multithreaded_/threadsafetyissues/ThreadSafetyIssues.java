package com.dyyhub.base.multithreaded_.threadsafetyissues;

import com.dyyhub.demo.T;

/**
 * @author dyyhub
 * @date 2022年06月26日 13:09
 * 线程安全问题解决方案之一 无同步方案
 * 线程1和线程2做的工作都是从0打到1000
 * 思考，这个num要不要用， 要用
 * 我们使用这个num的值过程中会不会改变他原来的值
 * 那改变了num值会影响其他线程吗
 * <p>
 * 解决方案， 把共享数据变成私有数据
 */
public class ThreadSafetyIssues {

    private static int num = 0;
    //这个final只是改变不了指向，至于这个threadLocal存了上面值他管不着
    public static final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            threadLocal.set(num);
            for (int i = 0; i < 1000; i++) {
                threadLocal.set(threadLocal.get() + 1);
                System.out.println("thread1-----" + threadLocal.get());
            }
        });
        Thread thread2 = new Thread(() -> {
            threadLocal.set(num);
            for (int i = 0; i < 1000; i++) {
                threadLocal.set(threadLocal.get() + 1);
                System.out.println("thread2-----" + threadLocal.get());
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println("最终num的值为：" + num);
    }
}
