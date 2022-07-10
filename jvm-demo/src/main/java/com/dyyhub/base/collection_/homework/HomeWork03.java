package com.dyyhub.base.collection_.homework;

import sun.reflect.generics.tree.Tree;

import java.util.TreeSet;

/**
 * @author dyyhub
 * @date 2022年06月20日 11:34
 * TreeSet的去重机制探讨
 */
public class HomeWork03 {
    public static void main(String[] args) {
        TreeSet treeSet = new TreeSet();
        treeSet.add("tom");
        treeSet.add("tom");
        System.out.println(treeSet);
        treeSet.add(new Person());//报错。没有实现Comparable接口
        //java.lang.ClassCastException :
        //Person cannot be cast to java.lang.Comparable
    }
}
class Person implements Comparable{
    @Override
    public int compareTo(Object o) {
        return 0;
    }
}