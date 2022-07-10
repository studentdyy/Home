package com.dyyhub.base.interface_;

/**
 * @author dyyhub
 * @date 2022年06月16日 11:12
 */
public class InterfaceExercise01 {
    public static void main(String[] args) {
        B b = new B();
        System.out.println(b.a);//1，
        //1，本身就是这样调用
        System.out.println(A.a);
        //1,B实现了A接口，当然可以拥有和使用A的成员
        System.out.println(B.a);
    }
}
interface A{
    int a = 1;//等价于 public static final int a = 1;
}
class B implements A{}