package com.dyyhub.base.wrapper.arrays_;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author dyyhub
 * @date 2022年06月17日 14:36
 * 定制化冒泡排序
 */
public class ArraysTest01 {
    public static void main(String[] args) {
        int[] arr = new int[]{90,-1,23,45,678,13};
        bubble(arr);
        System.out.println(Arrays.toString(arr));

        int[] arr2 = new int[]{90,-1,23,45,678,13};
        bubble2(arr2, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Integer i1 = (Integer)o1;
                Integer i2 = (Integer)o2;
                return i2 - i1;
            }
        });
        System.out.println(Arrays.toString(arr2));
    }

    public static void bubble(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            for(int j = 0; j < arr.length - 1; j++){
                if(arr[j] < arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
    public static void bubble2(int[] arr, Comparator c){
        for (int i = 0; i < arr.length - 1; i++) {
            for(int j = 0; j < arr.length - 1; j++){
                if(c.compare(arr[j],arr[j+1]) > 0){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
}
