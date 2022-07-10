package com.dyyhub.base.multithreaded_.threadsafetyissues;

import com.dyyhub.base.multithreaded_.utils.ThreadUtils;

/**
 * @author dyyhub
 * @date 2022年06月26日 21:52
 * 线程退出方法2
 * Interrupt()方法测试
 * 运行期间是不能打断的
 * wait和sleep是可以打断的。即阻塞过程是可以被打断的
 */
public class InterruptTest {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("begin");
            try {
                Thread.sleep(60000000);
            } catch (InterruptedException e) {
                System.out.println("我还没睡够呢");
                e.printStackTrace();
            }
            System.out.println("end");
        });
        thread.start();

        ThreadUtils.sleep(2000);
        thread.interrupt();


    }
}
