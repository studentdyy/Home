package com.dyyhub.base.wrapper.stringbuffer_;

/**
 * @author dyyhub
 * @date 2022年06月17日 11:43
 * StringBuffer练习题
 */
public class StringBufferTest01 {
    public static void main(String[] args) {
        String str = null;
        StringBuffer sb = new StringBuffer();
        sb.append(str);// if (str == null) return appendNull();
        //底层调用的是父类AbstractStringBuilder的appendNull()方法
        //返回“null”
        System.out.println(sb.toString());//null
        System.out.println(sb.toString());//4
        StringBuffer sb2 = new StringBuffer(null);//报错
    }
}
