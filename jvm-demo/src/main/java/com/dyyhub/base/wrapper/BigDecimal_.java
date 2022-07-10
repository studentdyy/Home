package com.dyyhub.base.wrapper;

import java.math.BigDecimal;

/**
 * @author dyyhub
 * @date 2022年06月17日 20:23
 */
public class BigDecimal_ {
    public static void main(String[] args) {
        BigDecimal b1 = new BigDecimal("123213213.97231");
        BigDecimal b2 = new BigDecimal("123");
        System.out.println(b1.add(b2));
        System.out.println(b1.subtract(b2));
        System.out.println(b1.multiply(b2));
        //BigDecimal.ROUND_CEILING精度问题 处理无限循环小数
        System.out.println(b1.divide(b2,BigDecimal.ROUND_CEILING));

    }
}
