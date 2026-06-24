package com.rpc.dubbo.realize.web.node02.sdk;

import com.rpc.dubbo.realize.web.node01.sdk.entity.PoJo;

import java.util.concurrent.CompletableFuture;

/**
 * @author shenlx
 * @description
 * @date 2025/1/7 17:26
 */
public interface GreetingServiceAsync {
    CompletableFuture<PoJo> sayHello(String name);

}
