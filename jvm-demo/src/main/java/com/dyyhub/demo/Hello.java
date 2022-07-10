package com.dyyhub.demo;

/**
 * @author dyyhub
 * @date 2022年05月19日 15:57
 */
//-Xms1024m -Xmx1024m -XX:+PrintGCDetails
//-Xms1024m -Xmx1024m -XX:+HeadDumpOnOutOfMemoryError
public class Hello {
    public static void main(String[] args) {
        //虚拟机视图使用的最大内存
        long max = Runtime.getRuntime().maxMemory();
        //jvm初始化的总内存
        long total = Runtime.getRuntime().totalMemory();

        System.out.println("max=" +max+ "字节\t" +(max/(double)1024/1024) + "MB");
        System.out.println("total=" +total+ "字节\t" +(total/(double)1024/1024) + "MB");

        //默认情况下，分配的总内存是电脑内存的1/4，而初始化的内存未：1/64
    }



}
