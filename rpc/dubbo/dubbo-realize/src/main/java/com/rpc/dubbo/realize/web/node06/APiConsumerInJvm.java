package com.rpc.dubbo.realize.web.node06;

import com.rpc.dubbo.realize.web.node06.sdk.service.GreetingService;
import com.rpc.dubbo.realize.web.node06.sdk.service.GreetingServiceImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.rpc.RpcContext;

/**
 * @author shenlx
 * @description
 * @date 2025/1/15 10:32
 */
public class APiConsumerInJvm {

    public static void exportService(){
        //创建ServiceConfig实例
        ServiceConfig<GreetingService> serviceConfig = new ServiceConfig<>();

        //设置应用程序配置
        serviceConfig.setApplication(new ApplicationConfig("first-dubbp-provider"));

        //设置服务注册中心信息
        RegistryConfig registryConfig = new RegistryConfig("zookeeper://127.0.0.1:2181");

        serviceConfig.setRegistry(registryConfig);

        //设置接口与实现类
        serviceConfig.setInterface(GreetingService.class);
        serviceConfig.setRef(new GreetingServiceImpl());

        //设置服务分组与版本
        serviceConfig.setVersion("1.0.5");
        serviceConfig.setGroup("dubbo6");

        //导出服务
        serviceConfig.export();

        //挂起线程，避免服务停止
        System.out.println("server is started");

    }

    public static void referService(){
        //创建服务引用实例对象
        ReferenceConfig<GreetingService> referenceConfig = new ReferenceConfig<>();
        //设置服务注册中心
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));

        //设置服务接口和超时时间
        referenceConfig.setInterface(GreetingService.class);
        referenceConfig.setTimeout(5000);

        referenceConfig.setVersion("1.0.5");
        referenceConfig.setGroup("dubbo6");
        referenceConfig.setCheck(false);

        //引用服务
        GreetingService greetingService=referenceConfig.get();

        //设置隐式参数
        RpcContext.getContext().setAttachment("company","alibaba");

        //调用服务
        System.out.println(greetingService.sayHello("hello-word"));
    }

    public static void main(String[]args) throws InterruptedException {
        //导出服务
        exportService();

        //引用服务
        referService();

        Thread.currentThread().join();
    }
}
