package com.dyyhub.base.poly.polyarr;

/**
 * @author dyyhub
 * @date 2022年06月14日 16:25
 */
public class Teacher extends Person{

    private double salary;

    public Teacher(String name, int age,double salary) {
        super(name, age);
        this.salary = salary;
    }

    @Override
    public String say() {
        return super.say() + "\tsalary=" + salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
