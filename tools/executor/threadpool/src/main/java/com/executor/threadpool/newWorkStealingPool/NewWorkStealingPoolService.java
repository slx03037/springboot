package com.executor.threadpool.newWorkStealingPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author shenlx
 * @description
 * @date 2026/3/6 16:30
 */
public class NewWorkStealingPoolService {

    /**
     * Executors.newWorkStealingPool()方法同样有两种构造方式，一种为newWorkStealingPool()，这种方式没有参数，
     * 另一种为newWorkStealingPool(int parallelism),这种相比前一种多了并行度参数
     */
    public static void newWorkStealingPool() {
        ExecutorService executor = Executors.newWorkStealingPool();
        for (int i = 0; i < 10; i++) {
            int j = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("第" + j + "个任务，当前线程名为:" + Thread.currentThread().getName());
                }
            });
        }
    }
}
