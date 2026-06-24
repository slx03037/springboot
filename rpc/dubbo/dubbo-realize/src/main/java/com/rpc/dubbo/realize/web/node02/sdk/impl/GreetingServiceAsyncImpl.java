package com.rpc.dubbo.realize.web.node02.sdk.impl;


import com.rpc.dubbo.realize.web.node01.sdk.entity.PoJo;
import com.rpc.dubbo.realize.web.node02.sdk.GreetingServiceAsync;
import org.apache.dubbo.common.utils.NamedThreadFactory;
import org.apache.dubbo.rpc.RpcContext;

import java.util.concurrent.*;

/**
 * @author shenlx
 * @description
 * @date 2025/1/13 11:02
 */
public class GreetingServiceAsyncImpl implements GreetingServiceAsync {
    //创建自定义线程
    private final ThreadPoolExecutor bizThreadPool=new ThreadPoolExecutor(8,16,1
            , TimeUnit.MINUTES,new SynchronousQueue<>()
            , new NamedThreadFactory("biz-thread-pool")
            , new ThreadPoolExecutor.CallerRunsPolicy());

    //创建服务处理接口，返回值为CompletetableFuture类型
    @Override
    public CompletableFuture<PoJo> sayHello(String name) {
        //2.1 为supplyAsync提供自定义线程池bizThreadPool,避免使用JDK公用线程池(ForkJoinPool.commonPool());
        //使用CompletableFuture.supplyAsync让服务处理异步
        //保存当前线程的上下文
        RpcContext context = RpcContext.getContext();
       // CompletableFuture future = (CompletableFuture) context.getFuture();
        return CompletableFuture.supplyAsync(()->{
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                System.out.println("报错原因:e:"+e.getMessage());
                e.printStackTrace();
            }
            PoJo p=new PoJo();
            System.out.println("async return");

            System.out.printf("hello:%s,%s%n",name,context.getAttachment("company"));
            p.setName(name);
            p.setId(context.getAttachment("company"));
//            return String.format("hello:%s,%s",name,context.getAttachment("company"));
            return p;
        },bizThreadPool);
    }
}
