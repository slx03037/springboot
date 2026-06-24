package com.rpc.dubbo.realize.web.node01.consumer;


import com.rpc.dubbo.realize.web.node01.sdk.service.GreetingService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @author shenlx
 * @description
 * @date 2025/1/13 10:45
 */
public class APiAsyncConsumerForCompletableFuture2 {

    public static void main(String[]args) throws InterruptedException {
        //1.创建服务引用对象，并配置数据
        ReferenceConfig<GreetingService> referenceConfig = new ReferenceConfig<>();

        referenceConfig.setApplication(new ApplicationConfig("first-dubbo-consumer"));

        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));

        referenceConfig.setInterface(GreetingService.class);

        referenceConfig.setTimeout(30000);

        referenceConfig.setVersion("1.0.0");

        referenceConfig.setGroup("dubbo");

        //2.设置异步
        referenceConfig.setAsync(true);

        //3.直接返回null
        GreetingService greetingService = referenceConfig.get();

        System.out.println("hello word");

        //异步执行回调

        /**
         * 注意：RpcContext是一个临时状态记录器，当接收到RPC请求，或发起RPC请求时，RpcContext的状态都会变化。
         * 比如：A调B，B再调C，则B机器上，在B调C之前，RpcContext记录的是A调B的信息，在B调C之后，RpcContext记录的是B调C的信息。
         * CompletableFuture<String> future = (CompletableFuture) RpcContext.getContext().getFuture();
         */
        RpcContext context = RpcContext.getContext();
        Future<String> future = context.getFuture();
        if(future instanceof CompletableFuture){
            ((CompletableFuture<String>) future).whenComplete((v,k)->{
               if(k !=null){
                   k.printStackTrace();
               }else {
                   System.out.println(v);
               }
            });
        }
        System.out.println("over");

        Thread.currentThread().join();
    }
}
