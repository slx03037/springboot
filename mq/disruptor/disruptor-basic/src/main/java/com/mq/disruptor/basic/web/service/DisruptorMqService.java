package com.mq.disruptor.basic.web.service;

/**
 * @author shenlx
 * @description
 * @date 2026/5/11 10:12
 */
public interface DisruptorMqService {
    /**
     * 消息
     * @param message
     */
    void sayHelloMq(String message);
}
