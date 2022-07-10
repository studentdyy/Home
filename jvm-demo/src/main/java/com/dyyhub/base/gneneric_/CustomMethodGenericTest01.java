package com.dyyhub.base.gneneric_;


/**
 * @author dyyhub
 * @date 2022年06月21日 10:29
 * 自定义泛型小练习
 */
public class CustomMethodGenericTest01 {
    public static void main(String[] args) {
        Apple<String,Integer,Double> apple = new Apple<>();
        apple.fly(10);//Integer
        apple.fly(new Monkey());//Monkey

    }
}
class Apple<T,R,M>{
    public <E> void fly(E e){
        System.out.println(e.getClass().getSimpleName());
    }
//    public void eat(U u){};报错没有U类型
    public void run(M m){};
}
class Monkey{}