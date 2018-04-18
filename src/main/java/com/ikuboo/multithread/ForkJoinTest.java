package com.ikuboo.multithread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 测试多线程刷数据
 * @author yuanchunsen@jd.com
 *         2018/3/22.
 */
public class ForkJoinTest {

    // 测试
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);

        CountTask countTask = new CountTask(0, 4170000);
        ForkJoinTask<Integer> result = forkJoinPool.submit(countTask);
        System.out.println(result.get());
    }
}




class CountTask extends RecursiveTask<Integer> {
    private int begin;
    private int pageSize;

    public CountTask(int begin, int pageSize) {
        this.begin = begin;
        this.pageSize = pageSize;
    }

    @Override
    protected Integer compute() {

        boolean canCompute = pageSize <= 4000;
        if (canCompute) {
            System.out.println(String.format("select * from xxxxx limit %d %d ", begin, pageSize));
            return pageSize;
        } else {
            int middle = pageSize / 2;
            CountTask countTask1 = new CountTask(begin, middle);
            CountTask countTask2 = new CountTask(begin + middle, pageSize % 2 == 0 ? middle: middle + 1);

            invokeAll(countTask1, countTask2);

            Integer join1 = countTask1.join();
            Integer join2 = countTask2.join();
            return join1 + join2;
        }

    }
}