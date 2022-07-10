package com.dyyhub.base.draw;

import javax.swing.*;
import java.awt.*;

/**
 * @author dyyhub
 * @date 2022年06月21日 10:58
 * 画一个圆
 */
public class DrawCircle extends JFrame{//JFrame对应窗口，画框

    private MyPanel mp = null;

    public static void main(String[] args) {
        new DrawCircle();
    }
    public DrawCircle(){
        mp = new MyPanel();
        this.add(mp);
        this.setSize(400,300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
//1,先定义一个MyPanel，继承JPanel类
class MyPanel extends JPanel{
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawOval(10,10,100,100);
    }
}