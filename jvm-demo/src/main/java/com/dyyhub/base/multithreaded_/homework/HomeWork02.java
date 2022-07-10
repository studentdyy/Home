package com.dyyhub.base.multithreaded_.homework;

import com.dyyhub.base.multithreaded_.utils.ThreadUtils;

/**
 * @author dyyhub
 * @date 2022年06月29日 16:01
 */
public class HomeWork02 {
    public static volatile double MONEY = 10000;

    public static void main(String[] args) {
        new Thread(() -> {
            while(true){
                ThreadUtils.sleep(300);
                double temp = MONEY;
                if(MONEY > 0 &&  temp == MONEY){
                    MONEY -= 1000;
                    System.out.println("线程1取出1000，，，余额剩余" + MONEY);
                }else break;
            }
        }).start();

        new Thread(() -> {
            while(true){
                ThreadUtils.sleep(300);
                double temp = MONEY;
                if(MONEY > 0 &&  temp == MONEY){
                    MONEY -= 1000;
                    System.out.println("线程2取出1000，，，余额剩余" + MONEY);
                }else break;
            }
        }).start();
    }
}
