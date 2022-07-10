package com.dyyhub.base.collection_.homework;

import java.util.HashSet;
import java.util.Objects;

/**
 * @author dyyhub
 * @date 2022年06月20日 11:52
 */
public class HomeWork04 {
    public static void main(String[] args) {
        HashSet hashSet = new HashSet();
        Girl p1 = new Girl(1001,"AA");
        Girl p2 = new Girl(1002,"BB");
        hashSet.add(p1);
        hashSet.add(p2);
        p1.name = "CC";

        hashSet.remove(p1);//失败，因为p1.name修改了，计算hash值发现索引是空的
        //[Girl{id=1002, name='BB'}, Girl{id=1001, name='CC'}]
        System.out.println(hashSet);//所以有两个对象

        hashSet.add(new Girl(1001,"CC"));
        //[Girl{id=1002, name='BB'}, Girl{id=1001, name='CC'}, Girl{id=1001, name='CC'}]
        System.out.println(hashSet);

        hashSet.add(new Girl(1001,"AA"));//成功，与id=1001, name='CC'的索引值相同，
        //挂在它后面了

        //[Girl{id=1002, name='BB'}, Girl{id=1001, name='CC'},
        // Girl{id=1001, name='CC'}, Girl{id=1001, name='AA'}]
        System.out.println(hashSet);
    }
}
class Girl{
    int id;
    String name;

    public Girl(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Girl girl = (Girl) o;
        return id == girl.id &&
                Objects.equals(name, girl.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Girl{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}