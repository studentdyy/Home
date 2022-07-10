package com.dyyhub.tankgame01;

import javax.swing.*;
import java.awt.*;

/**
 * @author dyyhub
 * @date 2022年06月23日 10:23
 * 坦克大战的绘图区域
 */
public class MyPanel extends JPanel {
    //定义我的坦克
    Hero hero = null;

    public MyPanel(){
        hero = new Hero(100,100);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //填充画面
        g.fillRect(0,0,1000,750);
        //画出坦克
        drawTank(hero.getX(),hero.getY(),g,0,0);
        drawTank(hero.getX() + 100,hero.getY(),g,0,1);

    }

    /**
     *
     * @param x 坦克的左上角x坐标
     * @param y 坦克的左上角x坐标
     * @param g 画笔
     * @param direction 坦克方向（上下左右）
     * @param type  坦克类型
     */
    public void drawTank(int x,int y,Graphics g,int direction,int type){
        switch (type){
            case 0:
                g.setColor(Color.cyan);
                break;
            case 1:
                g.setColor(Color.yellow);
                break;
        }
        switch (direction){
            case 0:
                g.fill3DRect(x,y,10,60,false);//坦克左边的轮子
                g.fill3DRect(x+30,y,10,60,false);//坦克右边的轮子
                g.fill3DRect(x+10,y+10,20,40,false);//坦克身体
                g.fillOval(x+10,y+20,20,20);//炮台
                g.drawLine(x+20,y + 30,x + 20,y);//炮筒
                break;
            default:
                System.out.println("暂时没有处理");
        }

    }
}
