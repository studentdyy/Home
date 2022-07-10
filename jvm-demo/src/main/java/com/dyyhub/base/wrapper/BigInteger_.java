package com.dyyhub.base.wrapper;

import java.math.BigInteger;

/**
 * @author dyyhub
 * @date 2022年06月17日 20:22
 */
public class BigInteger_ {
    public static void main(String[] args) {
        BigInteger b1 = new BigInteger("1231231274561879451");
        BigInteger b2 = new BigInteger("200");
        System.out.println(b1.add(b2));
        System.out.println(b1.subtract(b2));
        System.out.println(b1.multiply(b2));
        System.out.println(b1.divide(b2));

    }
}
