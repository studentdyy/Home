package com.dyyhub.base.homework;

/**
 * @author dyyhub
 * @date 2022年06月16日 16:49
 * 局部内部类的使用
 */
public class HomeWork05 {
    public static void main(String[] args) {
        A a = new A();
        a.f1();
    }
}
class A{
    private String NAME = "hello";
    public void f1(){
        class  B{
            private final String NAME = "lal";
            public void show(){
                //局部内部类直接访问外部类的成员
                //若外部类跟内部类的属性重名
                System.out.println("NAME = " + NAME +"\t"+ A.this.NAME);
            }
        }
        B b = new B();
        b.show();
    }
}