package com.rpc.dubbo.realize.web.node05.consumer;

import com.rpc.dubbo.realize.web.node06.sdk.service.GreetingService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;

/**
 * @author shenlx
 * @description
 * @date 2025/1/14 16:17
 */
public class APiConsumerMock {
    public static void main(String[]args){
        //创建服务引用对象实例
        ReferenceConfig<GreetingService> referenceConfig = new ReferenceConfig<>();

        //设置应用程序信息
        referenceConfig.setApplication(new ApplicationConfig("fourth-dubbo-consumer"));

        //设置注册中心
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));

        //设置服务接口和超时时间
        referenceConfig.setInterface(GreetingService.class);

        referenceConfig.setTimeout(5000);

        //设置服务分组与版本
        referenceConfig.setVersion("1.0.3");
        referenceConfig.setGroup("dubbo4");

        //设置启动时候不检查服务提供者是否可用
        referenceConfig.setCheck(false);
        referenceConfig.setMock("true");

        //引用服务
        GreetingService greetingService = referenceConfig.get();

        //设置隐式参数
        RpcContext.getContext().setAttachment("company","alibaba");

        //调用服务
        System.out.println(greetingService.sayHello("world"));
    }
}
