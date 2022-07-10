package com.dyyhub.base.collection_.collectionInterface_.set_;

import java.util.LinkedHashSet;

/**
 * @author dyyhub
 * @date 2022年06月19日 9:58
 * LinkedHashSet特性，有序
 */
public class LinkedHashSetTest {
    public static void main(String[] args) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add("张三");
        linkedHashSet.add("张三");
        linkedHashSet.add("张四");
        linkedHashSet.add("张五");
        linkedHashSet.add("张六");
        linkedHashSet.add("张七");
        System.out.println(linkedHashSet);

    }
}
