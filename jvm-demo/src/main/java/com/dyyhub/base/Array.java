package com.dyyhub.base;

/**
 * @author dyyhub
 * @date 2022年06月06日 10:12
 * 数组相关操作
 * 一个升序数组插入一个数后还是升序
 */
public class Array {
    public static void main(String[] args) {
        int[] num = new int[]{4,-1,9,13,-5,30,21};
        int i = array2(num);
        System.out.println(i);

        int[] num2 = new int[]{4,5,9,13,25,30,46};
        int[] ints = array3(num2,14);
        for (int anInt : ints) {
            System.out.print(anInt + " ");
        }
        System.out.println();
        array4();
    }

    /**
     * 返回一个数组的最大值
     * @param num
     * @return
     */
    public static int array2(int[] num){
        int n = num.length;
        int max = num[0];
        int maxIndex = 0;
        for (int i = 1; i < n; i++) {
            if(num[i] > max){
                max = num[i];
                maxIndex = i;
            }
        }

        return maxIndex;
    }
    //在升序数组上 插入一个元素，还是升序
    public static int[] array3(int[] num,int x){
       int[] result = new int[num.length + 1];
       if (x <= num[0]){
           result[0] = x;
           for (int i = 1; i < result.length; i++) {
               result[i] = num[i - 1];
           }
       }else if (x >= num[num.length - 1]){
            result[num.length] = x;
            for (int i = 0; i < num.length; i++) {
                result[i] = num[i];
            }
        }else {
           int i = 1;
           while(x > num[i]){
               i++;
           }
           for (int j = 0; j < i; j++) {
               result[j] = num[j];
           }
           result[i] = x;
           for (int j = i + 1; j < num.length + 1; j++) {
               result[j] = num[j - 1];
           }
       }
       return result;
    }

    /**
     * 打印A-Z的字符
     */
    public static void array1(){
        char[] c = new char[26];
        for (int i = 0; i <26; i++) {
            c[i] = (char)('A' + i);
        }
        for (char c1 : c) {
            System.out.print(c1 + "\t");
        }
    }

    public static void array4(){
        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            int temp = (int) (Math.random() * 100);
            arr[i] = temp;
        }
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
