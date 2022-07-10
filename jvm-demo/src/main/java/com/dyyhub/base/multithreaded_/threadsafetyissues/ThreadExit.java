package com.dyyhub.base.multithreaded_.threadsafetyissues;

import com.dyyhub.base.multithreaded_.utils.ThreadUtils;

/**
 * @author dyyhub
 * @date 2022年06月26日 21:38
 * 线程退出方法1，人为操控
 */
public class ThreadExit {

    static boolean flag = false;

    public static void main(String[] args) {
        new Thread(()->{
            while (!flag){
                ThreadUtils.sleep(100);
                System.out.println(Thread.currentThread().getName() + "线程一直在运行。。。");
            }
            System.out.println(Thread.currentThread().getName() + "线程结束运行。。。");
        }).start();

        ThreadUtils.sleep(2000);
        flag = true;
        System.out.println("主线程已经结束");
    }
}
