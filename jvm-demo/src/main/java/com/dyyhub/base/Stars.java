package com.dyyhub.base;

/**
 * @author dyyhub
 * @date 2022年06月05日 11:25
 * 金字塔和空心金字塔的打印
 *     *
 *    ***
 *   *****
 *  *******
 * *********
 *     *
 *    * *
 *   *   *
 *  *     *
 * *********
 */
public class Stars {
    public static void main(String[] args) {
        //金字塔
        int n  = 9;
        for(int i  = 1 ; i <= n ;i += 2){
            for (int j = 0; j < (n - i) / 2; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }
            for (int j = 0; j < (n - i) / 2; j++) {
                System.out.print(" ");
            }
            System.out.println();
        }


        //空心金字塔
        int m  = 9;
        for(int i  = 1 ; i <= m ;i += 2){
            for (int j = 0; j < (m - i) / 2; j++) {
                System.out.print(" ");
            }

            if(i == 1){
                System.out.print("*");
            }else if(i != m){
                System.out.print("*");
                for (int j = 0; j < i - 2; j++) {
                    System.out.print(" ");
                }
                System.out.print("*");
            }else {
                for (int j = 1; j <= m; j++) {
                    System.out.print("*");
                }
            }

            for (int j = 0; j < (m - i) / 2; j++) {
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
