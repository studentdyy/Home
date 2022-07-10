package com.dyyhub.base.object;

/**
 * @author dyyhub
 * @date 2022年06月14日 17:23
 */
public class Test {
    public static void main(String[] args) {
        boolean ui = "ui".equals("ui");
        System.out.println(ui);

        Integer integer1 = new Integer(100);
        Integer integer2 = new Integer(100);
        System.out.println(integer1 == integer2);//false 不是同一个对象
        System.out.println(integer1.equals(integer2));//true 值相同
    }
}
