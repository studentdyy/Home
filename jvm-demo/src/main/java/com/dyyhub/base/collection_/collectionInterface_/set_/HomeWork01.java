package com.dyyhub.base.collection_.collectionInterface_.set_;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author dyyhub
 * @date 2022年06月18日 21:14
 */
public class HomeWork01 {
    public static void main(String[] args) {
        Set set = new HashSet();
        Employee jack1 = new Employee("jack", 18);
        Employee jack2 = new Employee("jack", 18);
        Employee tom = new Employee("tom", 25);
        set.add(jack1);
        set.add(jack2);
        set.add(tom);
        System.out.println(set);
    }
}
class Employee{
    private String name;
    private int age;

    public Employee(String name, int age) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return age == employee.age &&
                Objects.equals(name, employee.name);
    }
    //如果name 和 age 相同，返回同一个hash值
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}