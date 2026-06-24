package com.rpc.dubbo.realize.web.node03.provider;


import com.rpc.dubbo.realize.web.node03.sdk.GreetingServiceRpcContext;
import com.rpc.dubbo.realize.web.node03.sdk.impl.GreetingServiceAsyncContextImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.io.IOException;


/**
 * @author shenlx
 * @description
 * @date 2025/1/13 11:14
 */
public class ApiProviderForAsync {

    public static void main(String[]args) throws IOException {
        //1.创建服务发布实例，并进行设置
        ServiceConfig<GreetingServiceRpcContext> serviceConfig = new ServiceConfig<>();
        serviceConfig.setApplication(new ApplicationConfig("second-dubbo-provider"));
        serviceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        serviceConfig.setInterface(GreetingServiceRpcContext.class);
        serviceConfig.setRef(new GreetingServiceAsyncContextImpl());
        serviceConfig.setVersion("1.0.1");
        serviceConfig.setGroup("dubbo2");

        //2.设置线程池策略
//        HashMap<String, String> params = new HashMap<>();
//        params.put("threadPool","myThreadPool");
//        serviceConfig.setParameters(params);

        //3.导出服务
        serviceConfig.export();

        //4.线程阻塞
        System.out.println("service is start");
        System.in.read();
    }
}
