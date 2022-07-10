package com.dyyhub.base.abstract_;

/**
 * @author dyyhub
 * @date 2022年06月16日 10:01
 */
public class AbstractExercise01 {
    public static void main(String[] args) {
        Manager manager = new Manager("jack",13,5700);
        manager.work();
        CommonEmployee commonEmployee = new CommonEmployee("Tom",17,3400);
        commonEmployee.work();
    }
}


