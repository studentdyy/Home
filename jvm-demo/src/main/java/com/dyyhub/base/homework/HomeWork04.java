package com.dyyhub.base.homework;

/**
 * @author dyyhub
 * @date 2022年06月16日 16:42
 * 匿名内部类的使用
 */
public class HomeWork04 {
    public static void main(String[] args) {
        CallPhone callPhone = new CallPhone();
        callPhone.work(new ComputerInterface() {
            @Override
            public double work(double d1, double d2) {
                return d1 + d2;
            }
        },1,2);
    }
}
interface ComputerInterface{
    double work(double d1,double d2);
}
class CallPhone {
    public void work(ComputerInterface computerInterface, double d1, double d2) {
        double result = computerInterface.work(d1, d2);
        System.out.println(result);
    }
}