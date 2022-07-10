package com.dyyhub.demo;

/**
 * @author dyyhub
 * @date 2022年06月02日 20:08
 */
public class Meeting1 {
    public static int add(int n,int a){
        int sum = 0;
        int temp = 0;
        for(int i = 1 ; i <= n ; i++ ){
            temp = temp * 10 + a;
            sum += temp;
        }
        return sum;
    }

    public static void main(String[] args) {
        int add = add(5,2);
        System.out.println(add);
    }
}
