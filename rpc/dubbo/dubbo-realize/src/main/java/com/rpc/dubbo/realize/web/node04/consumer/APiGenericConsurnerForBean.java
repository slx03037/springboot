package com.rpc.dubbo.realize.web.node04.consumer;

import org.apache.dubbo.common.beanutil.JavaBeanDescriptor;
import org.apache.dubbo.common.beanutil.JavaBeanSerializeUtil;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;

/**
 * @author shenlx
 * @description
 * @date 2025/1/14 14:44
 */
public class APiGenericConsurnerForBean {
    public static void main(String[]args){
        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(new ApplicationConfig("third-dubbo-consumer"));
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        referenceConfig.setVersion("1.0.2");
        referenceConfig.setGroup("dubbo3");

        //设置泛型化引用，并且泛型化类型为bean
        referenceConfig.setInterface("com.xinwen.dubbo.node04.sdk.service.GreetingService");
        referenceConfig.setGeneric("bean");
        referenceConfig.setTimeout(5000);

        //引用org.apache.dubbo.rpc.service.GenericService替代所有接口
        GenericService greetingService = referenceConfig.get();

        //泛型调用，参数使用javaBean进行序列化
        JavaBeanDescriptor param = JavaBeanSerializeUtil.serialize("world");
        Object result = greetingService.$invoke("sayHello", new String[]{"java.lang.String"}
                , new Object[]{param});

        //结果反序列化
        result = JavaBeanSerializeUtil.deserialize((JavaBeanDescriptor) result);
        System.out.println(result);
    }
}
