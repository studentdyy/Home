package com.dyyhub.base.innerclass;

/**
 * @author dyyhub
 * @date 2022年06月16日 12:16
 * 匿名内部类
 */
public class anonymousInnerClass {//外部其他类
    public static void main(String[] args) {
        Outer o = new Outer();
        o.method();
    }
}
class Outer{//外部类
    private int n1 = 10;
    public void method(){
        //该类只使用一次，所以用匿名内部类来简化开发
        //cat 的编译类型是 IA
        //cat 的运行类型是 匿名内部类 命名格式为 外部类$1
        //jdk底层帮我们创建了一个匿名内部类去实现了接口，然后立刻
        //创建Outer$1的实例吧地址返回给cat
        Q cat = new Q(){
            @Override
            public void cry() {
                System.out.println("猫在哭。。。。");
            }
        };
        System.out.println(cat.getClass());
        cat.cry();
    }
}
interface Q{public void cry();}