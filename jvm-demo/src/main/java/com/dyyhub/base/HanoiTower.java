package com.dyyhub.base;

/**
 * @author dyyhub
 * @date 2022年06月13日 14:54
 * 汉诺塔
 */
public class HanoiTower {
    public static void move(int num,char a ,char b , char c ) {
        if(num == 1){
            System.out.println(a + "->" + c);
        }else {
            move(num - 1,a , c, b);
            System.out.println(a + "->" + c);
            move(num - 1,b , a ,c);
        }
    }

    public static void main(String[] args) {
        move(3,'A','B','C');
    }
}
