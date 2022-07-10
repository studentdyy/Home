package com.dyyhub.base.multithreaded_.utils;

/**
 * @author dyyhub
 * @date 2022年06月25日 15:25
 */
public class ThreadUtils {
    public static void sleep(int i) {
        try{Thread.sleep(i);
        }catch (InterruptedException  e){
            e.printStackTrace();
        }
    }
}
