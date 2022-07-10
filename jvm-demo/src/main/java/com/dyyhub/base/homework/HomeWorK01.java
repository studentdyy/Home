package com.dyyhub.base.homework;

import com.dyyhub.base.Array;

import java.util.Arrays;

/**
 * @author dyyhub
 * @date 2022年06月15日 14:48
 * 用冒泡排序遍历Person数组
 */
public class HomeWorK01 {
    public static void main(String[] args) {
        Person[] people = new Person[3];

        Person p1 = new Person("jack",19,"销售");
        Person p2 = new Person("smith",16,"程序员");
        Person p3 = new Person("Lala",17,"前台");

        people[0] = p1;
        people[1] = p2;
        people[2] = p3;

        System.out.println(Arrays.toString(people));

        for (int i = 0; i < people.length - 1; i++) {
            for (int j = 0; j < people.length - 1; j++) {
                if(people[j].getAge() > people[j + 1].getAge()){
                    Person temp = people[j];
                    people[j] = people[j+1];
                    people[j+1] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(people));


    }
}
class Person{
    private String name;
    private int age;
    private String job;

    public Person(String name, int age, String job) {
        this.name = name;
        this.age = age;
        this.job = job;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", job='" + job + '\'' +
                '}';
    }
}