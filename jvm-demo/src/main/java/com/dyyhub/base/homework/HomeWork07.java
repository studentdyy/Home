package com.dyyhub.base.homework;

/**
 * @author dyyhub
 * @date 2022年06月15日 15:17
 */
public class HomeWork07 extends Test{
    String name = "Jack";
    HomeWork07(){
        super();
        System.out.println("Demo");
    }
    HomeWork07(String name){
        super(name);
    }
    public void test(){
        System.out.println(super.name);
        System.out.println(this.name);
    }
    public static void main(String[] args) {
        new HomeWork07().test();//Test Demo Rose Jack
        new HomeWork07("john").test();//john jack
    }
}
class Test{
    String name = "Rose";
    Test(){System.out.println("Test");}
    Test(String name){this.name = name;//属性没有动态绑定机制，这里调用的是父类
    }
}