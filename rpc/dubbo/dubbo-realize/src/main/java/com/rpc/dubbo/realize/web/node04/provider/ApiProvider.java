package com.rpc.dubbo.realize.web.node04.provider;



import com.rpc.dubbo.realize.web.node04.sdk.service.GreetingService;
import com.rpc.dubbo.realize.web.node04.sdk.service.impl.GreetingServiceimpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.io.IOException;

/**
 * @author shenlx
 * @description
 * @date 2025/1/8 10:41
 */
public class ApiProvider {
    public static void main(String[] args) throws IOException {
        //1.创建serviceConfig实例
        ServiceConfig<GreetingService> serviceConfig = new ServiceConfig<>();

        //2.设置应用程序配置
        serviceConfig.setApplication(new ApplicationConfig("third-dubbo-consumer"));

        //3.设置服务注册中心
        RegistryConfig registryConfig=new RegistryConfig("zookeeper://127.0.0.1:2181");


        serviceConfig.setRegistry(registryConfig);

        //4设置接口与实现类
        serviceConfig.setInterface(GreetingService.class);
        serviceConfig.setRef(new GreetingServiceimpl());

        //5设置服务分组与版本
        serviceConfig.setVersion("1.0.2");
        serviceConfig.setGroup("dubbo3");

        //6.设置线程池策略
//        HashMap<String,String> params=new HashMap<>();
//        params.put("threadpool","mythreadpool");
//        serviceConfig.setParameters(params);

        //7.导出服务
        serviceConfig.export();

        //8.挂起线程,避免服务停止
        System.out.println("server is started");

        System.in.read();
    }

}
