package com.dyyhub.tankgame02;

/**
 * @author dyyhub
 * @date 2022年06月23日 14:50'
 * 敌人坦克
 */
public class EnemyTank extends Tank{

    public EnemyTank(int x, int y) {
        super(x, y);
        this.setDirection(2);
        this.setType(1);
    }

}
