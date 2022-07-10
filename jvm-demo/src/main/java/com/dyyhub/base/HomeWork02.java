package com.dyyhub.base;

import java.util.Scanner;

/**
 * @author dyyhub
 * @date 2022年06月13日 17:20
 * 猜拳比赛
 */
public class HomeWork02 {
    public static Double max(double[] nums) {
        if(nums == null || nums.length < 1 ){
            throw new RuntimeException("nums数组有误");
        }
        double max = nums[0];
        for (double num : nums) {
            max = Math.max(max,num);
        }
        return max;
    }
    public static int find(String nums,char c) {
        char[] chars = nums.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == c) {
                System.out.print(i + "\t");
            }
        }
        return -1;
    }

    public static double updatePrice(double price) {
        if (price > 150.0){
            return 150.0;
        }else if(price > 100 && price <= 150){
            return 100.0;
        }else return price;
    }
    public static void old(int userNum,int computerNum,String[] result,int i) {
        switch (userNum){
                    case 0:
                        System.out.println("玩家输入拳头");
                        if(computerNum == 0){
                            System.out.println("系统输入拳头");
                            result[i] = "平";
                        }else if(computerNum == 1){
                            System.out.println("系统输入剪刀");
                            result[i] = "胜";
                        }else {
                            System.out.println("系统输入布");
                            result[i] = "输";
                        }
                        break;
                    case 1:
                        System.out.println("玩家输入剪刀");
                        if(computerNum == 0){
                            System.out.println("系统输入拳头");
                            result[i] = "输";
                        }else if(computerNum == 1){
                            System.out.println("系统输入剪刀");
                            result[i] = "平";
                        }else {
                            System.out.println("系统输入布");
                            result[i] = "胜";
                        }
                        break;
                    case 2:
                        System.out.println("玩家输入布");
                        if(computerNum == 0){
                            System.out.println("系统输入拳头");
                            result[i] = "胜";
                        }else if(computerNum == 1){
                            System.out.println("系统输入剪刀");
                            result[i] = "输";
                        }else {
                            System.out.println("系统输入布");
                            result[i] = "平";
                        }
                        break;
                }
    }
    public static void main(String[] args) {
        //测试作业1，找最大的double值在一个double数组中
        /*double[] nums = new double[]{90.0,90.8};
        double max = max(nums);
        System.out.println(max);
        //测试作业2 查找字符串中符合元素的索引
        int v = find("helloword", 'p');
        System.out.println(v);
        //测试作业3，更改图书价格
        double v1 = updatePrice(45);
        System.out.println(v1);*/

        Scanner scanner = new Scanner(System.in);

        String[] result = new String[5];

        for (int i = 0; i < 5; i++) {
            int computerNum = (int)(Math.random() * 10) % 3 ;
            System.out.println("请输入0，1，2. 0 表示拳头，1表示剪刀，2表示布");
            int userNum = scanner.nextInt();
            if(userNum == 0 || userNum== 1 || userNum == 2 ){
                //旧的方法
//                old(userNum,computerNum,result,i);
                if(userNum == 0 && computerNum == 1 ){
                    result[i] = "赢";
                }else if(userNum == 1 && computerNum == 2){
                    result[i] = "赢";
                }else if(userNum == 2 && computerNum == 0){
                    result[i] = "赢";
                }else if(userNum == computerNum){
                    result[i] = "平";
                }else {
                    result[i] = "输";
                }


                System.out.println("========目前的胜负情况===========");
                for (String s : result) {
                    System.out.print(s + "\t");
                }
                System.out.println("\n\n");
            }else {
                System.out.println("输入错误");
                break;
            }
        }
    }
}
