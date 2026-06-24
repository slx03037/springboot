package com.rpc.dubbo.realize.web.node01.consumer;


import com.rpc.dubbo.realize.web.node01.sdk.service.GreetingService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.remoting.exchange.ResponseCallback;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.protocol.dubbo.FutureAdapter;

import java.util.concurrent.ExecutionException;

/**
 * @author shenlx
 * @description
 * @date 2025/1/8 11:27
 */
public class APiAsyncConsumerForCallBack {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //创建引用实例,并设置属性
        ReferenceConfig<GreetingService> referenceConfig = new ReferenceConfig<>();

        referenceConfig.setApplication(new ApplicationConfig("first-dubbo-consumer"));

        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));

        referenceConfig.setInterface(GreetingService.class);

        referenceConfig.setVersion("1.0.0");

        referenceConfig.setGroup("dubbo");

        referenceConfig.setTimeout(5000);

        //设置为异步
        referenceConfig.setAsync(true);

        //直接返回为null
        GreetingService greetingService = referenceConfig.get();

        System.out.println(greetingService.sayHello("hello word"));

        //异步执行回调
        FutureAdapter future = (FutureAdapter) RpcContext.getContext().getFuture();

        future.getFuture().setCallback(new ResponseCallback() {
            @Override
            public void done(Object response) {
                System.out.println("result:"+response);
            }

            @Override
            public void caught(Throwable exception) {
                System.out.println("exception:"+exception.getLocalizedMessage());
            }
        });

        Thread.currentThread().join();

    }
}
