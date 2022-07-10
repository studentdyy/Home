package com.dyyhub.base.collection_.mapInterface_;

import java.util.*;

/**
 * @author dyyhub
 * @date 2022年06月19日 10:51
 */
public class MapTest {
    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("no1","tom");
        map.put("no2","kitty");
        HashMap hs = new HashMap();
        hs.put("啊啊", "2");
        hs.put("2", "2");
        hs.put("3", "2");
        hs.put("4", "2");
        hs.put("5", "2");
        hs.put("6", "2");
        hs.put("7", "2");
        hs.put("8", "2");
        hs.put("9", "2");

        Set entrySet = hs.entrySet();//1.获取entrySet对象
        System.out.println(entrySet );//2.结果：正确输出了上面put的内容
        Object[] objects = entrySet .toArray();
        System.out.println(objects[3]);//3.结果：正确打印了5=2

        Iterator iterator = entrySet .iterator();//4.获取迭代器
        while (iterator.hasNext()){//5.迭代器遍历
            Map.Entry next = (Map.Entry) iterator.next();
            next.getKey();
            next.getValue();
        }

        Set set = map.entrySet();
        for (Object obj : set) {
            Map.Entry entry = (Map.Entry) obj ;
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }

        Set set1 = map.keySet();
        System.out.println(set1.getClass());//class java.util.HashMap$KeySet
        Collection values = map.values();
        System.out.println(values.getClass());//class java.util.HashMap$Values
    }
}
