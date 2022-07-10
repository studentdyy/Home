package com.dyyhub.base.multithreaded_;

/**
 * @author dyyhub
 * @date 2022年06月25日 14:46
 * 守护线程的基本使用方法
 */
public class DaemonExercise01 {
    public static void main(String[] args) {
        Thread thread1 =  new Thread(()->{
            int count = 10;
            Thread thread2 = new Thread(() ->{
                while(true){
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("我thread2是个守护线程！");
                }
            });
            thread2.setDaemon(true);
            thread2.start();
            while(count >= 0){
                try {
                    Thread.sleep(200);
                    System.out.println("我thread1是用户线程");
                    count--;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("用户线程结束");
        });
//        thread1.setDaemon(true);
        thread1.start();

    }
}
