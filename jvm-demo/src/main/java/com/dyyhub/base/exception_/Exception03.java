package com.dyyhub.base.exception_;

import java.util.Scanner;

/**
 * @author dyyhub
 * @date 2022年06月16日 20:18
 * 练习3
 */
public class Exception03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true){
                System.out.println("请输入一个整数");
                String i = scanner.next();
            try {
                int i1 = Integer.parseInt(i);
                System.out.println("输入的整数为：" + i1);
                break;
            }catch (Exception e){
                System.out.println("输入错误");
            }
        }

    }
}
