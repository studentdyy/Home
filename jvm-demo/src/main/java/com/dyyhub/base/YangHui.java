package com.dyyhub.base;

/**
 * @author dyyhub
 * @date 2022年06月06日 14:48
 * 杨辉三角的实现
 */
public class YangHui {
    public static void main(String[] args) {
        int[][] arr = new int[18][];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new int[i + 1];
            for (int j = 0; j < arr[i].length; j++) {
                if(j == 0 || j == arr[i].length - 1){
                    arr[i][j] = 1;
                }else {
                    arr[i][j] =arr[i-1][j] + arr[i-1][j - 1];
                }
            }
        }
        for (int[] ints : arr) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }
}
