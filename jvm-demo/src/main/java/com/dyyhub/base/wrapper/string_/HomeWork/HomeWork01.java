package com.dyyhub.base.wrapper.string_.HomeWork;

/**
 * @author dyyhub
 * @date 2022年06月17日 20:53
 * 字符串反转
 */
public class HomeWork01 {
    public static void main(String[] args) {
        String s = "abcdefg";
        String reverse = reverse(s,0,6);
        System.out.println(reverse);
    }
    public static String reverse(String str,int start,int end){
        System.out.println(str);
        char[] chars = str.toCharArray();
        int l = chars.length - 1;
        if(str == null) return "字符串为空";
        if(start >= l || start < 0)return "start输入越界或空指针";
        if(end <=start || end > l)return "end输入越界或空指针";
        while(start < end){
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            end--;
            start++;
        }
        return String.valueOf(chars);
    }
}
