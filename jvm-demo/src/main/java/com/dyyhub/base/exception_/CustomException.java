package com.dyyhub.base.exception_;

/**
 * @author dyyhub
 * @date 2022年06月16日 21:17
 * 自定义异常
 */
public class CustomException {
    public static void main(String[] args) {
        int age = 80;
        if(age >= 0  && age<= 100){
            throw new AgeException("年龄不能再0~100之间");
        }else {
            System.out.println("你的年龄正常");
        }
    }
}
class AgeException extends RuntimeException{
    public AgeException(String message){
        super(message);
    }
}