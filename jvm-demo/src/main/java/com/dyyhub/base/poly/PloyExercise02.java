package com.dyyhub.base.poly;

/**
 * @author dyyhub
 * @date 2022年06月14日 15:27
 * 多态练习题
 */
public class PloyExercise02 {
    public static void main(String[] args) {
        Sub s = new Sub();
        s.display();//20
        System.out.println(s.count);//20
        Base b = s;
        System.out.println(b == s);//true
        System.out.println(b.count);//10，属性看编译类型
        b.display();// 20，方法看运行类型
    }
}
class Base {
    int count = 10;
    public void display() {System.out.println(this.count);}
}
class Sub extends Base {
    int count = 20;
    public void display() {System.out.println(this.count); }
}