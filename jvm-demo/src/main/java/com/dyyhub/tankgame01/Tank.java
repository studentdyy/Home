package com.dyyhub.tankgame01;

/**
 * @author dyyhub
 * @date 2022年06月23日 10:22
 */
public class Tank {
    private int x;
    private int y;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
