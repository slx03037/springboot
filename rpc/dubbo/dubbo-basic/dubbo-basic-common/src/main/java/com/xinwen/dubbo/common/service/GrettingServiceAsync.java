package com.xinwen.dubbo.common.service;

import java.util.concurrent.CompletableFuture;

/**
 * @author shenlx
 * @description
 * @date 2025/1/7 17:26
 */
public interface GrettingServiceAsync {
    CompletableFuture<String> sayHello(String name);

}
