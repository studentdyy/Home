package com.dyyhub.base;

/**
 * @author dyyhub
 * @date 2022年06月13日 15:59
 * 可变参数练习
 */
public class VarParameterExercise {
    public static void test(String args,int... nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        System.out.println(args + "的总成绩为" + sum);
    }
    public static void main(String[] args) {
        test("zhangsan",13,56,7,8,976);

    }
}
