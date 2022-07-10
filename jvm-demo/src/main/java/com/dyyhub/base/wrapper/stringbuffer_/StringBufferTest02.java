package com.dyyhub.base.wrapper.stringbuffer_;

import java.util.Scanner;

/**
 * @author dyyhub
 * @date 2022年06月17日 12:16
 * 每三位打印一个逗号分隔123,567,567.90
 */
public class StringBufferTest02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入商品名字：");
        String next = scanner.next();
        System.out.println("请输入价格");
        String price = scanner.next();
        StringBuffer sb = new StringBuffer(price);
        //笨比代码
//        int i = sb.indexOf(".");
//        System.out.println(i);
//        int flag = i / 3;
//        while(flag >0){
//            sb.insert(i - 3 , ",");
//            i -= 3;
//            flag--;
//        }
        for (int i = sb.indexOf(".") - 3; i >0 ; i -= 3) {
            sb.insert(i  , ",");
        }
        System.out.println(next+"价格为:"+sb.toString());

    }
}
