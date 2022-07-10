package com.dyyhub.base.gneneric_;

import java.util.ArrayList;

/**
 * @author dyyhub
 * @date 2022年06月20日 12:36
 * 泛型demo
 */
public class GenericDemo {
    public static void main(String[] args) {
        //1,interface<T> ,HashSet<E>{}等等，T,E只能是引用类型
        ArrayList<Integer> arrayList1 = new ArrayList<>();
//        ArrayList<int> arrayList2 = new ArrayList<>();

        //2,在给泛型指定具体类型后，可以传入该类型或者子类类型
        C<A> c1 = new C<A>(new A());
        //打印class com.dyyhub.base.gneneric_.A
        c1.f();
//        C<A> c2 = new C<A>(new B());
        C<A> c2 = new C<A>(new B());
        //打印class com.dyyhub.base.gneneric_.B
        c2.f();


        ArrayList arrayList2 = new ArrayList<>();
        ArrayList<Object> arrayList3 = new ArrayList<>();
    }
}
class A{}
class B extends A{}
class C<E>{
    E e;
    public C(E e) {
        this.e = e;
    }
    void f(){
        System.out.println(e.getClass());
    };
}
