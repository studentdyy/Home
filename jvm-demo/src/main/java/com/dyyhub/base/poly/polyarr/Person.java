package com.dyyhub.base.poly.polyarr;

/**
 * @author dyyhub
 * @date 2022年06月14日 16:19
 */
public class Person {
    public String name;
    public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public String say(){
        return "name=" + name +"\tage=" + age;
    }
}

