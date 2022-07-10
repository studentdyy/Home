package com.dyyhub.base.collection_.collectionInterface_.set_;

import java.util.HashSet;
import java.util.Set;

/**
 * @author dyyhub
 * @date 2022年06月18日 15:13
 * HashSet练习
 */
@SuppressWarnings({"all"})
public class SetTest {
    public static void main(String[] args) {
        Set set = new HashSet();
        set.add(null);
        set.add("tom");
        set.add("tom");
        set.add("john");
        set.add("kitty");
        //面试题1
        //如何理解HashSet不能添加重复元素
        set.add(new Dog("tom"));//true
        set.add(new Dog("tom"));//true
        set.add(new String("tom"));//true
        set.add(new String("tom"));//false
        System.out.println("set=" + set);
    }
}
class Dog{
    String name;
    public Dog(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }
}