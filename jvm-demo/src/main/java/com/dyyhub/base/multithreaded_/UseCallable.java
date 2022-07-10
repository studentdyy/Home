package com.dyyhub.base.multithreaded_;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author dyyhub
 * @date 2022年06月25日 12:10
 * 有返回值的线程,应用场景得到总财报再计算
 */
public class UseCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        Thread.sleep(5000);
        return 2;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(1);
        FutureTask<Integer> futureTask = new FutureTask(new UseCallable());
        System.out.println(3);

        new Thread(futureTask).start();
        System.out.println(4);
        //futureTask.get()是阻塞式的，等它完成了主线程才会往下走
        Integer integer = futureTask.get();
        System.out.println(integer);

        System.out.println(5);
        //输出结果1，3，4，2，5
    }

}
