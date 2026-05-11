package com.mq.disruptor.basic.web.config;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.mq.disruptor.basic.web.factory.HelloEventFactory;
import com.mq.disruptor.basic.web.handler.HelloEventHandler;
import com.mq.disruptor.basic.web.model.MessageModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * @author shenlx
 * @description
 * @date 2026/5/11 10:08
 */
@Configuration
@Slf4j
public class MQManager {
    @Bean("messageModel")
    public RingBuffer<MessageModel> messageModelRingBuffer() {
        //定义用于事件处理的线程池， Disruptor通过java.util.concurrent.Executors提供的线程来触发consumer的事件处理
        //ExecutorService executor = createExecutor().execute();
        HelloEventFactory factory = new HelloEventFactory();
        //指定ringbuffer字节大小，必须为2的N次方（能将求模运算转为位运算提高效率），否则将影响效率
        int bufferSize = 1024 * 256;
        //定义事件处理线程池，即消费者线程池
        ThreadFactory threadFactory = ThreadFactoryBuilder.create()
                .setNamePrefix("device-message-")
                .build();
        //单线程模式，获取额外的性能
        Disruptor<MessageModel> disruptor = new Disruptor<>(factory, bufferSize,threadFactory,
                ProducerType.SINGLE, new BlockingWaitStrategy());
        //设置事件业务处理器---消费者
        disruptor.handleEventsWith(helloEventHandler());
        // 启动disruptor线程
        disruptor.start();
        //获取ringbuffer环，用于接取生产者生产的事件
        RingBuffer<MessageModel> ringBuffer = disruptor.getRingBuffer();
        return ringBuffer;
    }

    @Bean("helloEventHandler")
    public HelloEventHandler helloEventHandler() {
        return new HelloEventHandler();
    }

    private static ExecutorService executor = new ThreadPoolExecutor(10, 10,
            60L, TimeUnit.SECONDS,
            new ArrayBlockingQueue(10));

    private static ExecutorService createExecutorService(){
//        new ThreadPoolExecutor(
//                    10,
//                10,
//                  60L,
//                              TimeUnit.SECONDS,
//                              new ArrayBlockingQueue(10)
//        );

        return new ThreadPoolExecutor(10,
                10,
                60L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

    }

    protected Executor createExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数：线程池创建时候初始化的线程数
        executor.setCorePoolSize(10);
        // 最大线程数：线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
        executor.setMaxPoolSize(20);
        // 缓冲队列：用来缓冲执行任务的队列
        executor.setQueueCapacity(500);
        // 允许线程的空闲时间60秒：当超过了核心线程之外的线程在空闲时间到达之后会被销毁
        executor.setKeepAliveSeconds(60);
        // 线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池
        executor.setThreadNamePrefix("saveRecord-");
        // 缓冲队列满了之后的拒绝策略：由调用线程处理（一般是主线程）
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        executor.initialize();
        return executor;
    }
}
