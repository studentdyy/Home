package com.dyyhub.base.wrapper;

/**
 * @author dyyhub
 * @date 2022年06月16日 22:40
 * Integer练习
 */
public class IntegerTest01 {
    public static void main(String[] args) {
        Integer i = new Integer(1);
        Integer j = new Integer(1);
        System.out.println(i == j);//false
        //Integer再-128~127内有缓存
        Integer m = 1;//阅读Integer.valueOf()源码
        Integer n = 1;
        System.out.println(m == n);//true
        Integer x = 128;//阅读Integer.valueOf()源码
        Integer y = 128;
        System.out.println( y == x);//false
    }
}
