package com.dyyhub.base.multithreaded_;

import com.dyyhub.demo.T;

/**
 * @author dyyhub
 * @date 2022年06月23日 10:57
 * 创建线程的4种方式
 */
public class ThreadDemo01 {
    public static void main(String[] args) {
        //创建线程的方式一
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread1..start");
            }
        });
        thread1.start();

        Thread thread2 = new MyThread1();
        thread2.start();

        Thread thread3 = new Thread(new MyThread2());
        thread3.start();

        //创建线程的方式四 lambda 语法
        Thread thread4 = new Thread(()->{
            System.out.println("start new thread4");
        });

        System.out.println("main..start");
    }
}
//创建线程的方式二
class MyThread1 extends Thread{
    @Override
    public void run() {
        System.out.println("start new thread2");
    }
}
//创建线程的方式三
class MyThread2 implements Runnable{

    @Override
    public void run() {
        System.out.println("start new thread3");
    }
}