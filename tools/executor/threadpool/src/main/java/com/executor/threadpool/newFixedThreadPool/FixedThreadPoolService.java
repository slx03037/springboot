package com.executor.threadpool.newFixedThreadPool;

import java.util.concurrent.*;

/**
 * @author shenlx
 * @description 创建一个固定大小的线程池，可控制并发的线程数。
 * @date 2026/3/6 16:18
 */
public class FixedThreadPoolService {

    private final BlockingQueue<Runnable> workQueue;
    private final RejectedExecutionHandler handler;
    private final ThreadFactory threadFactory;
    private final int corePoolSize;
    private final int maximumPoolSize;
    private final long keepAliveTime;



    public static ExecutorService newFixedThreadPool(int nThreads) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        return threadPoolExecutor;
    }

    public FixedThreadPoolService(int corePoolSize,
                                  int maximumPoolSize,
                                  long keepAliveTime,
                                  TimeUnit unit,
                                  BlockingQueue<Runnable> workQueue) {
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
                Executors.defaultThreadFactory(), defaultHandler);
    }

    private static final RejectedExecutionHandler defaultHandler =
            new ThreadPoolExecutor.AbortPolicy();

    public FixedThreadPoolService(int corePoolSize,
                                  int maximumPoolSize,
                                  long keepAliveTime,
                                  TimeUnit unit,
                                  BlockingQueue<Runnable> workQueue,
                                  ThreadFactory threadFactory,
                                  RejectedExecutionHandler handler) {
        if (corePoolSize < 0 ||
                maximumPoolSize <= 0 ||
                maximumPoolSize < corePoolSize ||
                keepAliveTime < 0) {
            throw new IllegalArgumentException();
        }
        if (workQueue == null || threadFactory == null || handler == null) {
            throw new NullPointerException();
        }
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
        this.keepAliveTime = unit.toNanos(keepAliveTime);
        this.threadFactory = threadFactory;
        this.handler = handler;
    }

}
