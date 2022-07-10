package com.dyyhub.base.interface_;

/**
 * @author dyyhub
 * @date 2022年06月16日 12:00
 * 接口练习题
 */
public class InterfaceExercise04 {
    public static void main(String[] args) {

    }
}
interface ICat1{
    double test();
}
interface ICat2{
    int test();
}
/*
class ICat implements ICat1,ICat2{

    @Override
    public double test() {
        return 0;
    }
    //报错
    //'test()' in 'com.dyyhub.base.interface_.ICat'
    // clashes with 'test()' in 'com.dyyhub.base.interface_.ICat2';
    // attempting to use incompatible return type
}*/
