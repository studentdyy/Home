package com.dyyhub.base.exception_;

/**
 * @author dyyhub
 * @date 2022年06月16日 20:12
 * try catch练习2
 */
public class Exception02 {
    public static int method(){
        int i = 1;
        try{
            i++;//2
            String[] names = new String[3];
            if (names[1].equals("tom")) {//空指针异常
                System.out.println(i);
            }
            return 1;
        }catch (NullPointerException e){
            return ++i;//3 底层是将i=3 保存到一个临时变量中temp = 3
        }finally {
            ++i;//4
            System.out.println("i=" + i);//执行完finally后在返回临时变量
        }
    }
    public static void main(String[] args) {
        System.out.println(method());//i=4 3
    }
}
