package com.dyyhub.base.homework.smallchange;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author dyyhub
 * @date 2022年06月15日 10:32
 * 零钱通
 */
public class SmallChangeSys {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double money = 5000;
        List<Order> orderList = new ArrayList<>();


        while (true){
            System.out.println("==============欢迎进入零钱通系统==============");
            System.out.println("\t\t\t\t1,零钱通明细\t\t\t\t");
            System.out.println("\t\t\t\t2,收益入账\t\t\t\t");
            System.out.println("\t\t\t\t3,消费\t\t\t\t");
            System.out.println("\t\t\t\t4,退出\t\t\t\t");
            System.out.println("请选择1-4");
            int i = scanner.nextInt();
            if(i == 1){
                System.out.println("目前账户余额为：" + money);
                for (Order order : orderList) {
                    System.out.println(order.toString());
                }
                System.out.println("\n");
            }else if(i == 2){
                System.out.print("请输入进账数目");
                double in = scanner.nextDouble();
                if(in <= 0){
                    System.out.println("输入金额要大于0");
                    continue;
                }
                Order order = new Order(in,LocalDateTime.now());
//                System.out.println("进账"+ order.getIn() +"元" +"日期为"+order.getDate().format(pattern));
                money += order.getIn();
                order.setBalance(money);
                orderList.add(order);
            }else if(i == 3){
                System.out.print("请输入消费店名");
                String shopName = scanner.next();
                System.out.print("请输入消费金额");
                double out = scanner.nextDouble();
                if(out <= 0 || out > money){
                    System.out.println("消费金额要大于0小于余额");
                    continue;
                }
                Order order = new Order(shopName,out,LocalDateTime.now());
//                System.out.println("店铺名字为"+shopName+"消费"+ order.getOut() +"元" +"日期为"+order.getDate().format(pattern));
                money -= order.getOut();
                order.setBalance(money);
                orderList.add(order);
            }else if(i == 4){
                String choice = null;
                while(true){
                    System.out.println("确定要退出嘛，y/n");
                    choice = scanner.next();
                    if("y".equals(choice) || "n".equals(choice)){
                        break;
                    }
                    System.out.println("输入有误");
                }
                if("y".equals(choice)){
                    System.out.println("已退出");
                    break;
                }else {
                    System.out.println("重新进入系统");
                }
            }else {
                System.out.println("输入错误");
            }
        }
    }


}
