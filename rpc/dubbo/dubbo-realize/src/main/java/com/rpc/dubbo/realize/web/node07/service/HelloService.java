package com.rpc.dubbo.realize.web.node07.service;

/**
 * @author shenlx
 * @description 服务接口:在RPC中，定义生产者和消费者有一个共同的服务接口API
 * @date 2025/3/14 21:05
 */
public interface HelloService {
    String sayHello(String someBody);
}
