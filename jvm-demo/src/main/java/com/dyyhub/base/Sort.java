package com.dyyhub.base;

/**
 * @author dyyhub
 * @date 2022年06月06日 11:59
 * 冒泡排序算法
 */
public class Sort {
    public static void main(String[] args) {
        int[] num = new int[]{4,-1,9,13,-5,30,21};
        int[] ints = sort1(num);
        for (int anInt : ints) {
            System.out.print(anInt + "\t");
        }
    }

    private static int[] sort1(int[] num) {
        for (int j = 0; j < num.length - 1; j++) {
            for (int i = 0; i < num.length - 1; i++) {
                if(num[i] > num[i + 1]){
                    int temp = num[i];
                    num[i] = num[i+1];
                    num[i+1] = temp;
                }
            }
        }

        return num;
    }
}
