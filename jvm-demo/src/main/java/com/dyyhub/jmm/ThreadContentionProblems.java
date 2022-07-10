package com.dyyhub.jmm;

/**
 * @author dyyhub
 * @date 2022年06月26日 12:41、
 * 线程争抢问题
 */
public class ThreadContentionProblems {
    private static int COUNT = 0;
    public synchronized static void adder(){
        COUNT++;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                adder();
            }
        });
        Thread thread2 = new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                adder();
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("最后的结果是："+COUNT);
    }
}
