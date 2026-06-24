package com.rpc.dubbo.realize.web.node04.sdk.service;



import com.rpc.dubbo.realize.web.node04.sdk.entity.PoJo;
import com.rpc.dubbo.realize.web.node04.sdk.model.Result;

/**
 * @author shenlx
 * @description
 * @date 2025/1/7 17:22
 */
public interface GreetingService {
    String sayHello(String name);

    Result<String> testGeneric(PoJo poJo);
}
