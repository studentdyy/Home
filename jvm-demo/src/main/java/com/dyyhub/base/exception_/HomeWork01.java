package com.dyyhub.base.exception_;

import java.util.Scanner;

/**
 * @author dyyhub
 * @date 2022年06月17日 21:08
 * 异常处理练习题
 */
public class HomeWork01 {
    public static void main(String[] args) {
        try {
            String name = "ABCc";
            String pwd = "123123";
            String email = "12312@qq.com";
            userRegister(name,pwd,email);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名");
        String next = scanner.next();
        int length = next.length();
        if(length < 0 || length >3){
            throw new RuntimeException("用户名是1~3个字符");
        }

        System.out.println("请输入密码");
        String pwd = scanner.next();
        if(!(pwd.length() == 6 && Digital(pwd)) ){
            throw new RuntimeException("密码必须是六位数的阿拉伯字母");
        }

        System.out.println("请输入密码");
        String email = scanner.next();
        int i = email.indexOf("@");
        int j = email.indexOf(".");
        if(i < 0 || j < 0 || (j - i < 0)){
            throw new RuntimeException("邮箱必须包含@和. 并且@在.的前面");
        }

    }

    private static boolean Digital(String pwd) {
        char[] chars = pwd.toCharArray();
        for (char aChar : chars) {
            if(aChar < '0' || aChar > '9'){
                return false;
            }
        }
        return true;
    }
    private static boolean userRegister(String name,String pwd,String email){
        int length = name.length();
        if(length < 0 || length >3){
            throw new RuntimeException("用户名是1~3个字符");
        }
        if(!(pwd.length() == 6 && Digital(pwd)) ){
            throw new RuntimeException("密码必须是六位数的阿拉伯字母");
        }
        int i = email.indexOf("@");
        int j = email.indexOf(".");
        if(i < 0 || j < 0 || (j - i < 0)){
            throw new RuntimeException("邮箱必须包含@和. 并且@在.的前面");
        }
        return true;
    }
}
