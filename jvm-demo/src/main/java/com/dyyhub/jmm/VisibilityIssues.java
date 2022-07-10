package com.dyyhub.jmm;

import com.dyyhub.base.multithreaded_.utils.ThreadUtils;

/**
 * @author dyyhub
 * @date 2022年06月25日 21:37
 * 可见性问题
 * 在单线程环境中，如果向某个变量写入某个值，在没有其他写入操作的影响下，
 * 那么你总能取到你写入的那个值。然而在多线程环境中，
 * 当你的读操作和写操作在不同的线程中执行时，
 * 情况就并非你想象的理所当然，也就是说不满足多线程之间的可见性，
 * 所以为了确保多个线程之间对内存写入操作的可见性，必须使用同步机制。
 *
 * thread线程一直在高速缓存中读取isOver的值，不能感知主线程已经修改了isOver的值而退出循环，
 * 这就是可见性的问题，使用【volatile】关键字可以解决这个问题
 */
public class VisibilityIssues {
    private static volatile boolean isOver = false;
//    private static  boolean isOver = false;

    public static void main(String[] args) {
        Thread thread = new Thread(() ->{
            while(!isOver){}
            System.out.println("你看不见我");
        });

        thread.start();
        ThreadUtils.sleep(3000);
        isOver = true;
    }
}
