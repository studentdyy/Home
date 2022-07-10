package com.dyyhub.base.multithreaded_;

import com.dyyhub.demo.T;

/**
 * @author dyyhub
 * @date 2022年06月23日 11:14
 */
public class ThreadDemo02 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(()->{
            System.out.println("hello,thread1");
        });
        Thread thread2 = new Thread(()->{
            System.out.println("hello,thread2");
        });
        Thread thread3 = new Thread(()->{
            System.out.println("hello,thread3");
        });

        System.out.println("main,Start");

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();

        thread2.join();

        thread3.join();


        System.out.println("main,end");
    }
}
