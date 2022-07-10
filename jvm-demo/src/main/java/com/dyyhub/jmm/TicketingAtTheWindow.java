package com.dyyhub.jmm;

import com.dyyhub.base.multithreaded_.utils.ThreadUtils;

/**
 * @author dyyhub
 * @date 2022年06月26日 12:48
 * 窗口售票问题
 */
public class TicketingAtTheWindow {

    private static int TRICK = 100;


    public static void main(String[] args) {
        Thread thread1 = new Thread(()->{
            while (TRICK > 0) {
                ThreadUtils.sleep(200);
                synchronized (TicketingAtTheWindow.class){
                    System.out.println("窗口1售出了一张票,,余票剩余"+TRICK--+"张");
                }
            }

        });
        Thread thread2 = new Thread(()->{
            while (TRICK > 0) {
                ThreadUtils.sleep(200);
                synchronized (TicketingAtTheWindow.class){
                    System.out.println("窗口2售出了一张票,,余票剩余"+TRICK--+"张");
                }
            }
        });

        thread1.start();
        thread2.start();

    }
}
