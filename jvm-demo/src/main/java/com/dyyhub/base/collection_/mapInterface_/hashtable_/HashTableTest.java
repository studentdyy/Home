package com.dyyhub.base.collection_.mapInterface_.hashtable_;

import java.util.Hashtable;

/**
 * @author dyyhub
 * @date 2022年06月19日 18:41
 */
public class HashTableTest {
    public static void main(String[] args) {
        Hashtable hashtable = new Hashtable();
        //hashtable.put(null,null);//NullPointerException
        //hashtable.put(null,"tom");//NullPointerException
        //hashtable.put("tom",null);//NullPointerException
        hashtable.put(1,"key");
        hashtable.put(2,"lala");
        hashtable.put(2,"smith");
        hashtable.put(3,"key");
        System.out.println(hashtable.toString());
    }
}
