package com.dyyhub.base.poly;

/**
 * @author dyyhub
 * @date 2022年06月14日 15:44
 * 动态绑定
 */
public class DynamicBinding {
    public static void main(String[] args) {
        //编译类型为A 运行类型为B
        A a = new B();
        //调用方法看类型 是B，则调用B的方法
        System.out.println(a.sum());//40 //（2） 30
        System.out.println(a.sum1());//30 //（2） 20
    }
}
class A{
    public int i = 10;
    //getI方法还是调用B的  于是返回的是 20 + 10 = 30
    public int sum(){return getI() + 10; }
    //i 是属性 属性看编译，哪里声明哪里使用，A声明的I 于是用A的I
    //10 + 10 = 20
    public int sum1(){return i + 10;}
    public int getI() {return i;}
}
class B extends A{
    public int i = 20;
    //B没有sum 和 sum1 方法 去找父类
//    （2）public int sum(){ return i + 20;}
//    （2）public int sum1(){return i + 10;}
    public int getI() {return i;}
}