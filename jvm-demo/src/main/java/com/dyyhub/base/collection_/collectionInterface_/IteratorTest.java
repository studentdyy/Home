package com.dyyhub.base.collection_.collectionInterface_;


import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author dyyhub
 * @date 2022年06月18日 9:59
 * 迭代器的使用
 */
public class IteratorTest {
    public static void main(String[] args) {
        Book book1 = new Book("红楼梦","曹雪芹",59.0);
        Book book2 = new Book("三国演义","罗贯中",49.0);
        Book book3 = new Book("水浒传","施耐庵",39.0);
        ArrayList arrayList = new ArrayList();
        arrayList.add(book1);
        arrayList.add(book2);
        arrayList.add(book3);
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            Object next =  iterator.next();
            System.out.println(next.toString());
        }
    }
}
