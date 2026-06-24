package com.xinwen.dubbo.common.service;

import com.xinwen.dubbo.common.entity.PoJo;
import com.xinwen.dubbo.common.model.Result;

/**
 * @author shenlx
 * @description
 * @date 2025/1/7 17:22
 */
public interface GreetingService {
    String sayHello(String name);

    Result<String> testGeneric(PoJo poJo);
}
