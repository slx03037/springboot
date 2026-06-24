package com.rpc.dubbo.realize.web.node01.consumer;


import com.rpc.dubbo.realize.web.node01.sdk.service.GreetingService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author shenlx
 * @description
 * @date 2025/1/8 11:06
 */
public class APiAsyncConsumer {
    /**
     代码 1创建引用实例，并设置属性：代码 2设置调用为异步；代码 3进行服务引
     用并且调用 sayHello （） 方法，由于是异步调用，所以该方法马上返回 null ：代码 使用
     RpcContext.getContext().getFuture （） 获取 future 象，然后在需要获取真实响应结果的
     调用 future.get（）来获取响应结果（调用 fu re.get（）会阻塞调用线程直到结果返回〉
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //创建引用实例，并设置属性
        ReferenceConfig<GreetingService> referenceConfig = new ReferenceConfig<>();

        referenceConfig.setApplication(new ApplicationConfig("first-dubbo-consumer"));

        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));

        referenceConfig.setInterface(GreetingService.class);

        referenceConfig.setVersion("1.0.0");

        referenceConfig.setGroup("dubbo");

        //2设置异步
        referenceConfig.setAsync(true);

        //3.直接返回null
        GreetingService greetingService = referenceConfig.get();

        System.out.println(greetingService.sayHello("hello word"));

        //Thread.sleep(5000);

        //等待结果
        Future<String> future= RpcContext.getContext().getFuture();

        System.out.println(future.get());

    }


}
