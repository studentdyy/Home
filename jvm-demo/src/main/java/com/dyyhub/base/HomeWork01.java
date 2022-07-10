package com.dyyhub.base;

/**
 * @author dyyhub
 * @date 2022年06月05日 12:57
 * 打印A-Z以及a-z
 *
 */
public class HomeWork01 {
    public static int homeWork1(double money){
        int count = 0;
        while(money > 0){
            if(money > 50000){
                double temp = money * 0.05;
                money -= temp;
                count++;
            }else if(money >= 1000){
                money -= 1000;
                count++;
            }else {
                break;
            }
        }
        return count;
    }
    public static void homeWork2(){
        for (char c = 'a' ; c <= 'z' ; c++){
            System.out.print(c +"\t");
        }
        System.out.println();
        for (char c = 'Z' ; c >= 'A' ; c--){
            System.out.print(c +"\t");
        }
    }

    public static void main(String[] args) {
//        int i = homeWork1(100000);
//        System.out.println(i);
        homeWork2();
    }
}
