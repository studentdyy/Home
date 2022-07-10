package com.dyyhub.base.wrapper.string_;

/**
 * @author dyyhub
 * @date 2022年06月17日 11:06
 * String练习题
 */
public class StringTest04 {
    String str = new String("tom");
    final char[] ch = {'j','a','v','a'};
    public void change(String str,char ch[]){
        str = "java";
        ch[0] = 'h';
    }
    public static void main(String[] args) {
        StringTest04 ex = new StringTest04();
        //重点在于作用域的问题
        //ex.change(ex.str, ex.ch);中的str 是在栈中一个栈帧的一个引用，
        //直接指向常量池中的“java”
        ex.change(ex.str, ex.ch);
        //这里输出的ex.str 是在堆中ex对象成员里的str维护的
        // value中指向常量池中的“tom”
        System.out.println(ex.str + "\tand");//tom and
        System.out.println(ex.ch);//hava
    }
}
