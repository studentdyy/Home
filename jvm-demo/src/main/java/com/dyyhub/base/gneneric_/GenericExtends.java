package com.dyyhub.base.gneneric_;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dyyhub
 * @date 2022年06月21日 10:39
 */
public class GenericExtends {
    public static void main(String[] args) {

        Object o = new String("xx");

        //泛型没有继承性
//        List<Object> list = new ArrayList<String>();

    }
}
class AA {}
class BB extends AA{}
class CC extends BB{}