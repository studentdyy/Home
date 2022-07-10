package com.dyyhub.base.collection_.collectionInterface_.List_;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dyyhub
 * @date 2022年06月18日 10:47
 */
public class ListTest01 {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("hello1");
        list.add("hello2");
        list.add("hello3");
        list.add("hello4");
        list.add("hello5");
        list.add("hello6");
        list.add("hello7");
        list.add("hello8");
        list.add("hello9");
        list.add("hello10");
        System.out.println("lsit=" + list);
        //在2号位插入一个元素
        list.add(1,"dyyhub");
        System.out.println("lsit=" + list);
        //获取第5个元素
        System.out.println(list.get(4));
        //删除第6个元素
        System.out.println(list.remove(5));
        System.out.println("lsit=" + list);
        //修改第7个元素
        list.set(6,"三国演义");
        System.out.println("lsit=" + list);
    }
}
