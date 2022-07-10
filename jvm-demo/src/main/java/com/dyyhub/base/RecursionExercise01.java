package com.dyyhub.base;

/**
 * @author dyyhub
 * @date 2022年06月06日 18:01
 * 斐波那契数列以及猴子偷桃问题
 */
public class RecursionExercise01 {
    public static int fibonacci(int n){
        if(n >= 1){
            if (n == 1 || n== 2) {
                return 1;
            }else {
                return fibonacci(n - 1)+fibonacci(n - 2);
            }
        }else {
            System.out.println("要输入大于等于1的值");
            return 0;
        }
    }


    public static int MonkeyEatPeach(int day){
        if(day == 10){
            return 1;
        }else if (day >= 1 && day <= 9){
            return (MonkeyEatPeach(day + 1) + 1) * 2;
        }else {

            return -1;
        }
    }

    public static void main(String[] args) {
        int fibonacci = fibonacci(8);
        System.out.println(fibonacci);

//        int i = MonkeyEatPeach(1 );
//        System.out.println(i);
    }



}
