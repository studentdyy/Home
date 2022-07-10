package com.dyyhub.base.collection_.collectionInterface_.set_;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author dyyhub
 * @date 2022年06月19日 19:28
 */
public class TreeSetTest {
    public static void main(String[] args) {
        //1,当我们使用无参构造器创建TreeSet时仍然是无序的
        //若想按字母顺序表排序，可以传入一个Compator
//        TreeSet treeSet = new TreeSet();
        TreeSet treeSet = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                String str1 = (String)o1;
                String str2 = (String)o2;
                return str1.compareTo(str2);
            }
        });
        treeSet.add("jack");
        treeSet.add("tom");
        treeSet.add("c");
        treeSet.add("b");
        treeSet.add("a");
        treeSet.add("smith");
        System.out.println(treeSet);
    }
}
