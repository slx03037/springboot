package com.rpc.dubbo.realize.web.node01.consumer;


import com.rpc.dubbo.realize.web.node01.sdk.service.GreetingService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;

/**
 * @author shenlx
 * @description
 * @date 2025/1/8 10:52
 */
public class APiConsumer {

    public static void main(String[] args)  {
        //9.创建服务 引用对象实例
        ReferenceConfig<GreetingService> referenceConfig = new ReferenceConfig<>();

        //10设置应用程序信息
        referenceConfig.setApplication(new ApplicationConfig("first-dubbo-consumer"));

        //设置服务注册中心
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));

        //设置服务接口和超时时间
        referenceConfig.setInterface(GreetingService.class);
        referenceConfig.setTimeout(5000);

        //设置自定义负载均衡策略与集群容器策略
//        referenceConfig.setLoadbalance("myloadbalance");
//        referenceConfig.setCluster("myBroadcast");

        //设置服务与分组密码版本
        referenceConfig.setVersion("1.0.0");
        referenceConfig.setGroup("dubbo");

        //引用服务
        GreetingService greetingService = referenceConfig.get();

        //设置隐式参数
        RpcContext.getContext().setAttachment("company","alibaba");

        //调用服务
        System.out.println(greetingService.sayHello("hello word"));
    }
}
