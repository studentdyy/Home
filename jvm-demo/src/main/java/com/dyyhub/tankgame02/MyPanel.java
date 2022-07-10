package com.dyyhub.tankgame02;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * @author dyyhub
 * @date 2022年06月23日 10:23
 * 坦克大战的绘图区域
 */
public class MyPanel extends JPanel implements KeyListener {
    //定义我的坦克
    Hero hero = null;
    Vector<EnemyTank> enemyTanks = new Vector<>();
    int enemyTanksSize = 3;

    public MyPanel(){
        hero = new Hero(500,500);
        for (int i = 0; i < enemyTanksSize; i++) {
            enemyTanks.add(new EnemyTank((100 * (i + 1)),0));
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //填充画面
        g.fillRect(0,0,1000,750);
        //画出坦克
        drawTank(hero.getX(),hero.getY(),g,hero.getDirection(),hero.getType());

        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            drawTank(enemyTank.getX(),enemyTank.getY(),g,enemyTank.getDirection(),enemyTank.getType());
        }


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
        //direction表示坦克方向
        switch (direction){
            case 0://向上
                g.fill3DRect(x,y,10,60,false);//坦克左边的轮子
                g.fill3DRect(x+30,y,10,60,false);//坦克右边的轮子
                g.fill3DRect(x+10,y+10,20,40,false);//坦克身体
                g.fillOval(x+10,y+20,20,20);//炮台
                g.drawLine(x+20,y + 30,x + 20,y);//炮筒
                break;
            case 1://向右
                g.fill3DRect(x,y+10,60,10,false);
                g.fill3DRect(x,y + 40,60,10,false);
                g.fill3DRect(x+10,y+20,40,20,false);
                g.fillOval(x+20,y+20,20,20);
                g.drawLine(x+30,y + 30,x + 60,y + 30);
                break;
            case 2://向下
                g.fill3DRect(x,y,10,60,false);//坦克左边的轮子
                g.fill3DRect(x+30,y,10,60,false);//坦克右边的轮子
                g.fill3DRect(x+10,y+10,20,40,false);//坦克身体
                g.fillOval(x+10,y+20,20,20);//炮台
                g.drawLine(x+20,y + 30,x + 20,y + 60);//炮筒
                break;
            case 3://向左
                g.fill3DRect(x,y+10,60,10,false);
                g.fill3DRect(x,y + 40,60,10,false);
                g.fill3DRect(x+10,y+20,40,20,false);
                g.fillOval(x+20,y+20,20,20);
                g.drawLine(x+30,y + 30,x ,y + 30);
                break;
            default:
                System.out.println("暂时没有处理");
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            hero.setDirection(0);
            hero.moveUp();
        }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            hero.setDirection(2);
            hero.moveDown();
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            hero.setDirection(3);
            hero.moveLeft();
        }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            hero.setDirection(1);
            hero.moveRight();
        }else {}
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
