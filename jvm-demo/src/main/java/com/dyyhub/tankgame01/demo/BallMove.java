package com.dyyhub.tankgame01.demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author dyyhub
 * @date 2022年06月23日 12:21
 * 演示小球移动
 */
public class BallMove extends JFrame{

    MyPanel mp = null;

    public static void main(String[] args) {
        new BallMove();
    }

    public BallMove(){
        mp = new MyPanel();
        this.add(mp);
        this.setSize(400,300);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
//面板
class MyPanel extends JPanel implements KeyListener {
    int x = 10;
    int y = 10;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x,y,20,20);
    }
    //有字符输出时，该方法就会触发
    @Override
    public void keyTyped(KeyEvent e) {

    }
    //有某个键按下，该方法就会触发
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println((char)e.getKeyCode()+"键盘被按下");
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            y+=3;
        }else if (e.getKeyCode() == KeyEvent.VK_UP){
            y-=3;
        }else if (e.getKeyCode() == KeyEvent.VK_LEFT){
            x-=3;
        }else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            x+=3;
        }else {}
        //重绘面板
        this.repaint();
    }
    //有某个键松开，该方法就会触发
    @Override
    public void keyReleased(KeyEvent e) {

    }
}