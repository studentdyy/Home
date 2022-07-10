package com.dyyhub.base;

/**
 * @author dyyhub
 * @date 2022年06月06日 12:12
 * 二维数组的创建和打印
 */
public class TwoDimensionalArray01 {
    public static void main(String[] args) {
        int[][] num = {{0,0,0,0,0},{0,0,1,0,0},{0,2,0,3,0},{0,0,0,0,0}};
        for (int[] ints : num) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }


}
