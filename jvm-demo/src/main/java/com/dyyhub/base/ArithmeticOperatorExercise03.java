package com.dyyhub.base;

/**
 * @author dyyhub
 * @date 2022年06月03日 18:32
 * 测试++x 和 x++的区别
 * ++x 是先运算在赋值
 * x++ 是先赋值在运算
 */
public class ArithmeticOperatorExercise03 {
    public static void test1(int x,int y){
        if(x++ == 6 & ++y == 6){
            x = 11;
        }
        System.out.println("x=" + x +"\ty=" + y);
    }
    public static void test2(int x,int y){
        if(x++ == 6 && ++y == 6){
            x = 11;
        }
        System.out.println("x=" + x +"\ty=" + y);
    }

    public static void test3(int x,int y){
        if(x++ == 6 | ++y == 6){
            x = 11;
        }
        System.out.println("x=" + x +"\ty=" + y);
    }
    public static void test4(int x,int y){
        if(x++ == 6 || ++y == 6){
            x = 11;
        }
        System.out.println("x=" + x +"\ty=" + y);
    }
    public static void test5(){
        boolean x = true;
        boolean y = false;
        short z = 46;
        if((z++ == 46 ) && (y=true)){
            z++;
        }
        if((x = false ) || (++z==49)){
            z++;
        }
        System.out.println("z=" + z);
    }
    public static void main(String[] args) {
        test1(5,5);
        test2(5,5);
        test3(5,5);
        test4(5,5);
        test5();
        int n1 = 55;
        int n2 = 56;
        int n3 = 57;
        int max1 = n1 > n2 ? n1 : n2;
        int max2 = max1 > n3 ? max1 : n3;
        System.out.println(max2);

        byte b = 19;
//        short s = b + 2;
    }
}
