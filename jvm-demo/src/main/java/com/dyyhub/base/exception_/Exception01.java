package com.dyyhub.base.exception_;

/**
 * @author dyyhub
 * @date 2022年06月16日 19:39
 * try catch练习1
 */
public class Exception01 {
    public static int method(){
        int i = 1;
        try{
            i++;//2
            String[] names = new String[3];
            if (names[1].equals("tom")) {//空指针异常
                System.out.println(i);
            }
        }catch (NullPointerException e){
            return i++;//3
        }finally {
            return ++i;//4
        }
    }
    public static void main(String[] args) {
        System.out.println(method());//4
    }
}
