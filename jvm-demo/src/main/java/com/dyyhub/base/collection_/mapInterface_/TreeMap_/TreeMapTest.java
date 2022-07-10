package com.dyyhub.base.collection_.mapInterface_.TreeMap_;

import com.dyyhub.demo.T;

import java.util.Comparator;
import java.util.TreeMap;

/**
 * @author dyyhub
 * @date 2022年06月19日 20:05
 */
public class TreeMapTest {
    public static void main(String[] args) {
        TreeMap treeMap = new TreeMap(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                String str1 = (String)o1;
                String str2 = (String)o2;
                return str1.length() - str2.length();
            }
        });
        treeMap.put("tom","汤姆");
        treeMap.put("jack","杰克");
        treeMap.put("tom","杰克");
        treeMap.put("abcd","杰克");
        System.out.println(treeMap);//{tom=杰克, jack=杰克}
    }
}
