package com.rpc.dubbo.realize.web.node03.sdk.impl;


import com.rpc.dubbo.realize.web.node03.sdk.GreetingServiceRpcContext;
import org.apache.dubbo.common.utils.NamedThreadFactory;
import org.apache.dubbo.rpc.AsyncContext;
import org.apache.dubbo.rpc.RpcContext;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author shenlx
 * @description
 * @date 2025/1/13 14:01
 */
public class GreetingServiceAsyncContextImpl implements GreetingServiceRpcContext {

    //创建自定义线程
    private final ThreadPoolExecutor bizThreadPool=new ThreadPoolExecutor(8,16,1
            , TimeUnit.MINUTES,new SynchronousQueue<>()
            , new NamedThreadFactory("biz-thread-pool")
            , new ThreadPoolExecutor.CallerRunsPolicy());

    //创建服务处理接口，返回值为CompletableFuture
    @Override
    public String sayHello(String name) {
        //1.开启异步
        final  AsyncContext asyncContext = RpcContext.startAsync();
        bizThreadPool.execute(()->{
            //如果要使用上下文,则必须要放在第一句执行
            asyncContext.signalContextSwitch();
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.getMessage();
            }
            //写响应
            asyncContext.write(String.format("hello:%s-%s",name,RpcContext.getContext().getAttachment("company")));
        });
        return null;
    }
}
