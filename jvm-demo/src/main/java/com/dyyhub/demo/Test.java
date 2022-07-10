package com.dyyhub.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dyyhub
 * @date 2022年06月01日 18:24
 */
public class Test {
    String str = new String("good");
    char ch[] = {'a','b','c'};


    public void change(String str, char[] ch){
        str = str.substring(0,2);
        ch[0]='g';
    }


    public static void main(String[] args) {
        Test test = new Test();
        test.change(test.str,test.ch);
        System.out.println(test.str + " and "+new String(test.ch) );


        List<Class> list = new ArrayList<>();
        A a = new A();
        B b = new B();
        T t = new T();
        list.add(T.class);

        int i = 1;
        switch (i){
            case 0:
                System.out.println("zero");
            case 1:
                System.out.println("one");
            case 2:
                System.out.println("two");
            default:
                System.out.println("default");
        }

    }


}
