package com.rpc.dubbo.realize.web.node03.consumer;


import com.rpc.dubbo.realize.web.node03.sdk.GreetingServiceRpcContext;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;

/**
 * @author shenlx
 * @description
 * @date 2025/1/13 11:20
 */
public class ApiConsumerForProviderAsync {
    public static void main(String[]args) throws InterruptedException {
        //创建引用实例，并进行设置
        ReferenceConfig<GreetingServiceRpcContext> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(new ApplicationConfig("second-dubbo-consumer"));
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        referenceConfig.setInterface(GreetingServiceRpcContext.class);
        //referenceConfig.setAsync(true);
        referenceConfig.setTimeout(50000);
        //referenceConfig.setCluster("myCluster");
        referenceConfig.setVersion("1.0.1");
        referenceConfig.setGroup("dubbo2");

        //服务引用
        GreetingServiceRpcContext greetingServiceAsync = referenceConfig.get();

        //设置隐式参数
        RpcContext.getContext().setAttachment("company","alibaba");
        //RpcContext.getContext().set("ip","127.0.0.1");


        //4.设置future并回调
        String str= greetingServiceAsync.sayHello("hello-word");
        System.out.println(str);
//        future.whenComplete((v,k)->{
//            if(k !=null){
//                System.out.println("异常：v:"+v+"k:"+k);
//                k.printStackTrace();
//            }else {
//                System.out.println(v);
//            }
//        });
        Thread.currentThread().join();
    }
}
