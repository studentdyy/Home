package com.dyyhub.base.collection_.utils;

import com.dyyhub.demo.A;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author dyyhub
 * @date 2022年06月19日 20:56
 */
public class CollectionsDemo {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("tom");
        arrayList.add("smith");
        arrayList.add("jack");
        arrayList.add("malian");
        //反转
        Collections.reverse(arrayList);
        System.out.println(arrayList);//[malian, jack, smith, tom]
        //随机排序
        Collections.shuffle(arrayList);
        System.out.println(arrayList);//[jack, tom, smith, malian]
        //升序排列
        Collections.sort(arrayList);
        System.out.println(arrayList);//[jack, malian, smith, tom]
        //按字符串长度排序
        Collections.sort(arrayList, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((String)o1).length() - ((String)o2).length();
            }
        });
        System.out.println(arrayList);//[tom, jack, smith, malian]
        //将指定list集合中的i j 元素交换
        Collections.swap(arrayList,1,2);
        System.out.println(arrayList);//[tom, smith, jack, malian]

        //Object max(Collection):根据元素的自然规律，返回给定集合中的最大元素
        System.out.println("自然顺序最大元素" + Collections.max(arrayList));

        //Object max(Collection,Comparator):根据元素的自然规律，返回给定集合中的最大元素
        System.out.println("字符串长度最大元素" + Collections.max(arrayList, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((String)o1).length() - ((String)o2).length();
            }
        }));
        //Object min(Collection)
        //Object min(Collection,Comparator)

        //int frequency(Collection.object):返回指定集合中指定元素出现的次数
        System.out.println("tom元素出现的次数"+ Collections.frequency(arrayList,"tom"));

        //void copy(List dest,List src)：将src中的内容赋值到dest中
        ArrayList dest = new ArrayList();
        for (int i = 0; i < 4; i++) {
            dest.add("");
        }
        Collections.copy(dest,arrayList);
        System.out.println("dest="+dest);
        //boolean replaceAll
        Collections.replaceAll(arrayList,"tom","汤姆");
        System.out.println(arrayList);
    }
}
