package com.dyyhub.base;

/**
 * @author dyyhub
 * @date 2022年06月03日 18:32
 */
public class ArithmeticOperatorExercise01 {
    public static void main(String[] args) {
        int i = 1 ;
        i = i ++;//规则使用临时变量.
        //i++ 先赋值后运算
        // (1)temp=i;(2)i=i+1(3)i=temp
        System.out.println(i);

        int j = 1 ;
        j = ++j;//规则使用临时变量.
        //++j 先运算后赋值
        // (1)temp=i;(2)i=i+1(3)i=temp
        System.out.println(j);
    }
}
