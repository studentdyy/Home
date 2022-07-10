package com.dyyhub.base.collection_.homework;

import com.dyyhub.demo.A;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author dyyhub
 * @date 2022年06月20日 10:03
 * arraylist练习题，打印新闻标题
 */
public class HomeWork01 {
    public static void main(String[] args) {
        News news1 = new News("新冠确诊病例超千万，数百万印度教信徒赴恒河\"圣浴\"引民众担忧");
        News news2 = new News("男子突然想起2个月前钓的鱼还在网兜里，捞起一看赶紧放生");
        News news3 = new News("麻利麻利哄");
        ArrayList arrayList = new ArrayList();
        arrayList.add(news1);
        arrayList.add(news2);
        arrayList.add(news3);
        //倒序遍历
        for (int i = arrayList.size() - 1; i >= 0 ; i--) {
            News news = (News)arrayList.get(i);
            String head = news.getHead();
            if(head.length() >= 15){
                String substring = head.substring(0, 15);
                System.out.println(substring+"...");
            }else {
                System.out.println(head);
            }
        }

        Collections.reverse(arrayList);
        for (Object o : arrayList) {
            News news = (News)o;
            String head = news.getHead();
            if(head.length() >= 15){
                String substring = head.substring(0, 15);
                System.out.println(substring+"...");
            }else {
                System.out.println(head);
            }
        }
    }
}
class News{
    String head;
    String content;

    public News(String head) {
        this.head = head;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "news{" +
                "head='" + head + '\'' +
                '}';
    }
}