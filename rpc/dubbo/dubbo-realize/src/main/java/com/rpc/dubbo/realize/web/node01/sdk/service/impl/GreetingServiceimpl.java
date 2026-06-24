package com.rpc.dubbo.realize.web.node01.sdk.service.impl;



import com.rpc.dubbo.realize.web.node01.sdk.entity.PoJo;
import com.rpc.dubbo.realize.web.node01.sdk.model.Result;
import com.rpc.dubbo.realize.web.node01.sdk.service.GreetingService;
import org.apache.dubbo.common.json.JSON;
import org.apache.dubbo.rpc.RpcContext;

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

        result.setSucess (true) ;

        try {
            result.setData(JSON.json(poJo));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
