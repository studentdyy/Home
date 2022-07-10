package com.dyyhub.base.wrapper;

/**
 * @author dyyhub
 * @date 2022年06月16日 22:09
 * 三元运算符是一个整体
 * 自动装箱与自动拆箱
 */
public class WrapperType {
    public static void main(String[] args) {
        //Boolean
        //Character
        Integer i = new Integer(10);
        int x = i; //自动拆箱，Integer.intValue(i)
        Float f = 1.0f;//自动装箱，相当于Float.valueOf(1.0f)
        Object obj1 = true ? new Integer(1) : new Double(2.3);
        System.out.println(obj1);//1.0
    }
}
