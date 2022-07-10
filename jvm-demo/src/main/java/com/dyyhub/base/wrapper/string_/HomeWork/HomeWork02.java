package com.dyyhub.base.wrapper.string_.HomeWork;

/**
 * @author dyyhub
 * @date 2022年06月18日 8:28
 * String 练习题
 * 输入 Willian Jefferson Clinton
 * 输出 Clinton,Willian .J
 */
public class HomeWork02 {
    public static void main(String[] args) {
        String name = "Willian Jefferson Clinton";
//        String[] s = name.split(" ");
//        String result = s[2] + ", " + s[0] +" ."+s[1].substring(0,1);
//        System.out.println(result);
        String s = printName(name);
        System.out.println(s);

    }

    public static String printName(String str){
        if(str == null) return "不能为空";
        String[] s = str.split(" ");
        if(s.length != 3)return "长度不符合";
        String format = String.format("%s,%s .%c", s[2], s[0], s[1].toUpperCase().charAt(0));
        return format;
    }
}
