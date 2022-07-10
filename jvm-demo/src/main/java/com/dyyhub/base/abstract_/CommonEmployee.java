package com.dyyhub.base.abstract_;

/**
 * @author dyyhub
 * @date 2022年06月16日 10:08
 */
public class CommonEmployee extends Employee{

    public CommonEmployee(String name, int id, double salary) {
        super(name, id, salary);
    }

    @Override
    public void work() {
        System.out.println("普通员工"+getName()+"正在工作。。。");
    }
}
