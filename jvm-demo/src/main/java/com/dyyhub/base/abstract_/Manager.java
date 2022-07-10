package com.dyyhub.base.abstract_;

/**
 * @author dyyhub
 * @date 2022年06月16日 10:04
 */
public class Manager extends Employee{

    private double bonus;

    public Manager(String name, int id, double salary) {
        super(name, id, salary);
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public void work() {
        System.out.println("经理"+getName()+"正在工作。。。");
    }
}
