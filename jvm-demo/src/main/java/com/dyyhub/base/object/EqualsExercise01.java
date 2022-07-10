package com.dyyhub.base.object;

/**
 * @author dyyhub
 * @date 2022年06月14日 20:17
 * Object类中的 equals 的相关练习
 */
public class EqualsExercise01 {
    public static void main(String[] args) {
        Person p1 = new Person("zhangsan",15,'m');
        Person p2 = new Person("zhangsan",15,'m');
        System.out.println(p1.equals(p2));
//        System.out.println("hello" == new java.sql.Date());
    }

}

class Person{
    private String name;
    private int age;
    private char gender;

    public Person(String name, int age, char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj instanceof Person){
            Person p = (Person) obj;
//            if (this.age != p.age) return false;
//            if (this.gender != p.gender) return false;
//            if (super.equals(this.name)) return false;
//            return true;
            return this.name.equals(p.name) && this.name == p.name && this.gender == p.gender;
        }
        return false;
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

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
}