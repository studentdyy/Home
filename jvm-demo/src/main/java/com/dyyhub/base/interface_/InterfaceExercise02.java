package com.dyyhub.base.interface_;

/**
 * @author dyyhub
 * @date 2022年06月16日 11:27
 * 接口的多态传递现象
 */
public class InterfaceExercise02 {
    public static void main(String[] args) {
        IA c1 = new C();
        IB c2 = new C();
        //C 实现了IB 接口,IB接口继承了IC接口，相当于C也实现了IC接口
    }
}
interface IA{
    void test();
}
interface IB extends IA{}
class C implements IB{
    @Override
    public void test() {
    }
}