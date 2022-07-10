package com.dyyhub.base.poly;

/**
 * @author dyyhub
 * @date 2022年06月14日 15:16
 * 向上转型跟向下转型的练习
 */
public class PloyExercise01 {
    public static void main(String[] args) {
        double b = 13.4;
        long l =  (long)b;
        System.out.println(l);//13
        int in = 1;
        //boolean d = (boolean) in;
        Object obj = "Hello";//向上转型
        String objStr = (String) obj;//向下转型
        System.out.println(objStr);//Hello

        Object objPri = new Integer(5);//可以向上转型
        String str = (String) objPri;//错误，吧指向一个Integer的父类引用，转成string
        Integer str1 = (Integer)objPri;//可以，正确的向下转型
    }
}
