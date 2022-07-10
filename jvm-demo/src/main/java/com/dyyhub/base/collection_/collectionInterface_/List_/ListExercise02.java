package com.dyyhub.base.collection_.collectionInterface_.List_;


import com.dyyhub.base.collection_.collectionInterface_.Book;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author dyyhub
 * @date 2022年06月18日 10:56
 * 集合sort练习题
 */
public class ListExercise02 {
    public static void main(String[] args) {
        Book book1 = new Book("红楼梦","曹雪芹",59.0);
        Book book2 = new Book("三国演义","罗贯中",149.0);
        Book book3 = new Book("水浒传","施耐庵",39.0);
        List arrayList = new ArrayList();
        arrayList.add(book1);
        arrayList.add(book2);
        arrayList.add(book3);
        sort(arrayList);
        for (Object book : arrayList) {
            Book bookTemp = (Book)book;
            String format = String.format("名称：%s\t\t价格：%s\t\t作者：%s",
                    bookTemp.getName(), bookTemp.getPrice(), bookTemp.getAuthor());
            System.out.println(format);
        }
        Iterator iterator = arrayList.iterator();

    }
    public static void sort(List list){
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - 1; j++) {
                Book book1 = (Book) list.get(j);
                Book book2 = (Book) list.get(j + 1);
                if(book1.getPrice() > book2.getPrice()){
                    list.set(j,book2);
                    list.set(j + 1,book1);
                }
            }
        }
    }
}
