package com.dyyhub.demo;

/**
 * @author dyyhub
 * @date 2022年06月01日 18:39
 */
//java基础
public class A extends T{
    char a ='\n';
    char b ='\t';
    char c ='\r';
    char d ='\\';



    public static void main(String[] args) {
        A a = new A();
        System.out.println(a.a);
        String book1 = "天龙";
        String book2 = "啦啦啦";
        System.out.println(book1 + book2);

        char c1 = '男';
        char c2 = '女';
        int age = 20;
        float score = 80.9f;
        String hobby = "打篮球";
        System.out.println(c1 + c2);
        System.out.println("姓名\t年龄\t成绩\t性别\t爱好\n"+
                book1+"\t" + age + "\t" + score  +"\t"+ c1 + "\t" + hobby);
        String s = String.valueOf(2);
        int i = Integer.parseInt("123");
        System.out.println(i);
    }
}
