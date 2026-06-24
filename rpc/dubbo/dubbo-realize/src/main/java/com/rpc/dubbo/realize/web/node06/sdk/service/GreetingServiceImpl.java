package com.rpc.dubbo.realize.web.node06.sdk.service;

import com.rpc.dubbo.realize.web.node06.sdk.entity.PoJo;
import com.rpc.dubbo.realize.web.node06.sdk.model.Result;

/**
 * @author shenlx
 * @description
 * @date 2025/1/14 16:14
 */
public class GreetingServiceImpl implements GreetingService {
    @Override
    public String sayHello(String name) {
        return "mock value";
    }

    @Override
    public Result<String> testGeneric(PoJo poJo) {
        return null;
    }
}
