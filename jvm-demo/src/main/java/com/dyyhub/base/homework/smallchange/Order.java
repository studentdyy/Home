package com.dyyhub.base.homework.smallchange;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author dyyhub
 * @date 2022年06月15日 10:44
 */
public class Order {
    private double in;
    private double out;
    private LocalDateTime date;
    private String shopName;
    private static final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private double Balance;

    public Order(String shopName , double out, LocalDateTime date) {
        this.shopName = shopName;
        this.out = out;
        this.date = date;
    }

    public Order(double in,LocalDateTime date) {
        this.in = in;
        this.date = date;
    }

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double balance) {
        Balance = balance;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public double getIn() {
        return in;
    }

    public void setIn(double in) {
        this.in = in;
    }

    public double getOut() {
        return out;
    }

    public void setOut(double out) {
        this.out = out;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        if(this.out == 0){
            return "收益进账" +"\t+" + this.in +"\t"+this.getDate().format(pattern)+"\t\t余额：" + this.getBalance();
        }else {
            return this.getShopName() +"\t-" + this.out + "\t" + this.getDate().format(pattern)+"\t\t余额：" + this.getBalance();
        }
    }
}
