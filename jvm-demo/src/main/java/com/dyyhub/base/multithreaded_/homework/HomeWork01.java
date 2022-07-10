package com.dyyhub.base.multithreaded_.homework;

import com.dyyhub.base.multithreaded_.utils.ThreadUtils;

import java.util.Scanner;

/**
 * @author dyyhub
 * @date 2022年06月29日 14:10
 * 练习题线程1一直打印，线程2监听到用户输入q线程1就停止
 * 老韩是创建了两个类，B类持有A类对象，然后监听flag的值，有get set方法
 */
public class HomeWork01 {

    volatile static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            while (flag) {
                int temp = (int) (Math.random() * 100);
                System.out.println(Thread.currentThread().getName() + "--随机z整数为:" + temp);
                ThreadUtils.sleep(200);
            }

            System.out.println(Thread.currentThread().getName() + "已退出");
        });
        Thread thread2 = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入字符");
            String next = scanner.next();
            if (next.toUpperCase().charAt(0) == 'Q') {
                flag = false;
            }
        });

        thread2.start();
        thread1.start();
        thread2.join();
        thread1.join();

    }
}
