package com.dyyhub.base;

/**
 * @author dyyhub
 * @date 2022年06月03日 18:32
 * 取余操作
 */
public class ArithmeticOperatorExercise02 {
    public static void test(int n){
        int day = n % 7;
        int week = n / 7;
        System.out.println(n +"天" + "转换为:"+week+"星期"+day+"天");
    }
    public static void main(String[] args) {
        test(25911);
    }
}
