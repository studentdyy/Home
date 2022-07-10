package com.dyyhub.base.poly.polyarr;

/**
 * @author dyyhub
 * @date 2022年06月14日 16:23
 */
public class Student extends Person{
    private double score;

    public Student(String name, int age,double score) {
        super(name, age);
        this.score = score;
    }

    @Override
    public String say() {
        return super.say() + "\tscore=" + this.score ;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
