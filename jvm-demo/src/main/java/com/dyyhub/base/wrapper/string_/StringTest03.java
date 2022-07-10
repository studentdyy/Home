package com.dyyhub.base.wrapper.string_;

/**
 * @author dyyhub
 * @date 2022年06月17日 10:39
 * 字符串相加底层细节
 */
public class StringTest03 {
    public static void main(String[] args) {
        String a = "abc";
        String b = "Hello";
        //1,先创建一个springBuilder sb = springBuilder()，底层是初始值是16的char数组
        //2,sb.append("abc");
        //3,sb.append("hello");
        //4,sb.toString(){return new String(value,0,count)}
        //最后c 指向的是 堆中的对象(String) value[] -> 池中的 “abcHello”
        String c = a + b;
    }
}
