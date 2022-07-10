package com.dyyhub.tankgame02;

import javax.swing.*;

/**
 * @author dyyhub
 * @date 2022年06月23日 10:26
 */
public class TankGame02 extends JFrame {
    MyPanel mp = null;

    public static void main(String[] args) {
        TankGame02 tankGame02 = new TankGame02();
    }

    public TankGame02(){
        mp = new MyPanel();
        this.add(mp);
        this.setSize(1000,750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(mp);
        this.setVisible(true);
    }

}
