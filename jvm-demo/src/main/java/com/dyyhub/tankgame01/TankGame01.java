package com.dyyhub.tankgame01;

import javax.swing.*;

/**
 * @author dyyhub
 * @date 2022年06月23日 10:26
 */
public class TankGame01 extends JFrame {
    MyPanel mp = null;

    public static void main(String[] args) {
        TankGame01 tankGame01 = new TankGame01();
    }

    public TankGame01(){
        mp = new MyPanel();
        this.add(mp);
        this.setSize(1000,750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}
