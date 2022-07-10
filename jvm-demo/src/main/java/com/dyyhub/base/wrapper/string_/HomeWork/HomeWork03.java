package com.dyyhub.base.wrapper.string_.HomeWork;

/**
 * @author dyyhub
 * @date 2022年06月18日 8:39
 * String练习题，打印字符串字母数量和数字数量
 */
public class HomeWork03 {
    public static void main(String[] args) {
        String str = "NameDyy123";
        count(str);
    }
    public static void count(String str){
        char[] chars = str.toCharArray();
        int upper = 0;
        int lower = 0;
        int num = 0;
        for (char aChar : chars) {
            if(aChar <= 'z' && aChar >= 'a'){
                lower++;
            }else if(aChar <= 'Z' && aChar >= 'A'){
                upper++;
            }else if(aChar <= '9' && aChar >= '0'){
                num++;
            }else {
                throw new RuntimeException("输入错误，不是英文字母和数字");
            }
        }
        String format = String.format("该字符串大写字母有%s个,小写字母有%s个,数字有%s个", upper, lower, num);
        System.out.println(format);
    }
}
