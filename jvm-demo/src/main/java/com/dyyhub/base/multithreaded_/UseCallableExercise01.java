package com.dyyhub.base.multithreaded_;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author dyyhub
 * @date 2022年06月25日 13:36
 * 单线程计算1到1亿的和以及 多线程计算1到1亿的和 demo
 */
public class UseCallableExercise01 implements Callable<Long> {
    private int from;
    private int to;

    public UseCallableExercise01() {
    }

    public UseCallableExercise01(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public Long call() throws Exception {
        Long result = 0L;
        for (int i = from; i <to; i++) {
            result += i;
        }
        return result;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        Long num = 0L;
        for (int i = 0; i < 100000000; i++) {
            num += i;
        }
        long end = System.currentTimeMillis();
        System.out.println(num);
        System.out.println("单线程 time = " + (end - start));

        long start2 = System.currentTimeMillis();
        FutureTask<Long> futureTask1 = new FutureTask(new UseCallableExercise01(0,20000000));
        FutureTask<Long> futureTask2 = new FutureTask(new UseCallableExercise01(20000000,40000000));
        FutureTask<Long> futureTask3 = new FutureTask(new UseCallableExercise01(40000000,60000000));
        FutureTask<Long> futureTask4 = new FutureTask(new UseCallableExercise01(60000000,80000000));
        FutureTask<Long> futureTask5 = new FutureTask(new UseCallableExercise01(80000000,100000000));
        new Thread(futureTask1).start();
        new Thread(futureTask2).start();
        new Thread(futureTask3).start();
        new Thread(futureTask4).start();
        new Thread(futureTask5).start();

        Long Long1 = futureTask1.get();
        Long Long2 = futureTask2.get();
        Long Long3 = futureTask3.get();
        Long Long4 = futureTask4.get();
        Long Long5 = futureTask5.get();
        long end2 = System.currentTimeMillis();
        System.out.println(Long1 + Long2 + Long3 + Long4 + Long5);
        System.out.println("多线程 time = " + (end2 - start2));

        System.out.println("====================老师教你怎么写======================");
        start = System.currentTimeMillis();
        num = 0L;
        FutureTask[] futureTasks = new FutureTask[10];
        for (int i = 0; i < 10; i++) {
            FutureTask<Integer> task = new FutureTask(new UseCallableExercise01(i *10000000,(i+1) * 10000000));
            new Thread(task).start();
            futureTasks[i] = task;
        }
        for (int i = 0; i < futureTasks.length; i++) {
            Long o = (Long)futureTasks[i].get();
            num += o;
        }
        end = System.currentTimeMillis();
        System.out.println(num);
        System.out.println("多线程2 time = " + (end - start));

    }
}
