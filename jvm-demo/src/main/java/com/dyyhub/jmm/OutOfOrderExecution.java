package com.dyyhub.jmm;

/**
 * @author dyyhub
 * @date 2022年06月25日 18:44
 * 指令重排问题
 * as-if-serial
 * JMM-java内存模型在执行程序时，为了提高性能，编译器和处理器常常会对指令做重排序
 * 我们发现结果中绝大部分是感觉正确的，（0，1），但是也有（1，0），这两种结果都是正确的，
 * 一个是线程1先执行，一个是线程二先执行，其实只要次数足够多哪种情况都会有。
 * 但是按道理绝对不会出现（0，0），因为出现零的情况一定是x = b; y = a; a = 1; b = 1;
 * 如果出现了也就证明了我们的执行在执行的时候确实存在乱序。
 */
public class OutOfOrderExecution {
    private static int x = 0, y = 0;
    private static int a = 0, b = 0;
    private static int count = 0;

    private static volatile int NUM = 0;

    public static void main(String[] args)
            throws InterruptedException {
        long start = System.currentTimeMillis();
        for (;;) {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    a = 1;
                    x = b;
                }
            });
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    b = 1;
                    y = a;
                }
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println("一共执行了：" + (count++) + "次");
            //当y = a 34行 以及 x = b 27行代码先执行的时候 就退出
            //说明 没有依赖的话就是乱序执行
            if(x==0 && y==0){
                long end = System.currentTimeMillis();
                System.out.println("耗时：+"+ (end-start) +"毫秒，(" + x + "," + y + ")");
                break;
            }
            a=0;b=0;x=0;y=0;
        }
    }
}