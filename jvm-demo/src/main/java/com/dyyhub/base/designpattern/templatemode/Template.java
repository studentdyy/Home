package com.dyyhub.base.designpattern.templatemode;

/**
 * @author dyyhub
 * @date 2022年06月16日 10:25
 */
abstract public class Template {
    abstract void job();
    public String name;
    public Template(String name) {
        this.name = name;
    }
    public void Time(){
        long start = System.currentTimeMillis();
        job();
        long end = System.currentTimeMillis();
        System.out.println(this.name + "执行任务的时间为" + (end - start) + "秒");
    }
}
