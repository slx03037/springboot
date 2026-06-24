package com.xinwen.dubbo.common.service.impl;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.rpc.RpcContext;
import com.xinwen.dubbo.common.entity.PoJo;
import com.xinwen.dubbo.common.model.Result;
import com.xinwen.dubbo.common.service.GreetingService;

import java.io.IOException;

/**
 * @author shenlx
 * @description
 * @date 2025/1/7 17:27
 */
public class GreetingServiceimpl  implements GreetingService {
    @Override
    public String sayHello(String name) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return String.format("Hello:%s-%s", name, RpcContext.getContext().getAttachment("company"));
    }
    @Override
    public Result<String> testGeneric(PoJo poJo) {
        Result<String> result = new Result<String> ();

        result .setSucess (true) ;

        try {
            result.setData(JSON. json(poJo));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
