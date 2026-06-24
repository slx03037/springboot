package com.rpc.dubbo.realize.web.node04.consumer;

import org.apache.dubbo.common.Constants;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.common.io.UnsafeByteArrayInputStream;
import org.apache.dubbo.common.io.UnsafeByteArrayOutputStream;
import org.apache.dubbo.common.serialize.Serialization;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author shenlx
 * @description
 * @date 2025/1/14 15:11
 */
public class APiGenericConsurnerForNativeJava {
    public static void main(String[]args) throws IOException, ClassNotFoundException {
        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(new ApplicationConfig("third-dubbo-consumer"));
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        referenceConfig.setVersion("1.0.2");
        referenceConfig.setGroup("dubbo3");

        //设置泛型化引用，并且泛型化类型为bean
        referenceConfig.setInterface("com.xinwen.dubbo.node04.sdk.service.GreetingService");
        referenceConfig.setGeneric("nativeJava");
        referenceConfig.setTimeout(5000);

        //3.用org.apache.dubbo.rpc.service.GenericService替代所有接口
        GenericService greetingService=referenceConfig.get();

        UnsafeByteArrayOutputStream outputStream = new UnsafeByteArrayOutputStream();

        //4.泛型引用，需要把参数使用java序列化为二进制
         ExtensionLoader.getExtensionLoader(Serialization.class)
                .getExtension(Constants.GENERIC_SERIALIZATION_NATIVE_JAVA)
                 .serialize(null,outputStream).writeObject("world");

        Object result = greetingService.$invoke("sayHello", new String[]{"java.lang.String"}
                , new Object[]{outputStream.toByteArray()});

        //打印结果，需要把二进制结果使用Java反序列化为对象
        UnsafeByteArrayInputStream inputStream = new UnsafeByteArrayInputStream((byte[]) result);

        System.out.println(ExtensionLoader.getExtensionLoader(Serialization.class)
                .getExtension(Constants.GENERIC_SERIALIZATION_NATIVE_JAVA)
                .deserialize(null,inputStream)
                .readObject());
    }
}
