package com.dyyhub.base.interface_;

/**
 * @author dyyhub
 * @date 2022年06月16日 11:33
 * 课堂练习
 */
public class InterfaceExercise03 {
    public static void main(String[] args) {
        CC cc = new CC();
        cc.test();
    }
}
interface AA{int x = 0;}
class BB {int x = 1;}
class CC extends BB implements AA{
    public void test(){
        //访问接口的 用接口名.x下，父类的x用super，
        //直接访问x会报错
        //System.out.println(x);
        System.out.println(AA.x +" " + super.x);
    }
}
