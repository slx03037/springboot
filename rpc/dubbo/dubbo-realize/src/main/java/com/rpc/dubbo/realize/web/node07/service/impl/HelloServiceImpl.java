package com.rpc.dubbo.realize.web.node07.service.impl;

import com.rpc.dubbo.realize.web.node07.service.HelloService;

/**
 * @author shenlx
 * @description 服务实现
 * @date 2025/3/14 21:06
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String someBody) {
        return "hello"+someBody+"!";
    }
}
