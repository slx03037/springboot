package com.rpc.dubbo.realize.web.node04.consumer;

import org.apache.dubbo.common.json.JSON;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shenlx
 * @description
 * @date 2025/1/14 11:44
 */
public class APiGenericConsurnerForTrue {
    public static void main(String[]args) throws IOException {
        //1.泛型参数固定为
        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(new ApplicationConfig("third-dubbo-consumer"));
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        referenceConfig.setVersion("1.0.2");
        referenceConfig.setGroup("dubbo3");

        //设置为泛型引用,类型为true
        referenceConfig.setInterface("com.xinwen.dubbo.node04.sdk.service.GreetingService");
        referenceConfig.setGeneric(true);
        referenceConfig.setTimeout(5000);

        //3.用org.apache.dubbo.rpc.service.GenericService替代所有接口
        GenericService greetingService=referenceConfig.get();

        //4.泛型引用，基本类型以及Date,List,Map等不需要转换，直接调用，如果返回值为POJO，也将自动转换为Map
        Object result = greetingService.$invoke("sayHello", new String[]{"java.lang.String"}
                , new Object[]{"world"});

        System.out.println(JSON.json(result));

        //pojo参数转换Map
        Map<String,Object> map=new HashMap<>();
        map.put("class","com.xinwen.dubbo.node04.sdk.entity.PoJo");
        map.put("id","1993");
        map.put("name","jiaduo");

        //发起泛型引用
        result = greetingService.$invoke("testGeneric", new String[]{"com.xinwen.dubbo.node04.sdk.entity.PoJo"}
                , new Object[]{map});
        System.out.println(result);
    }
}
