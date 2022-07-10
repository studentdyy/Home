package com.dyyhub.demo;

/**
 * @author dyyhub
 * @date 2022年06月01日 18:39
 */
public class B extends A{
    public static void main(String[] args) {
        int i = 1 ;
        i = i ++;//规则使用临时变量.
        //i++ 先运算后赋值
        // (1)temp=i;(2)i=i+1(3)i=temp
        System.out.println(i);
    }

}
