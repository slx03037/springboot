package com.mq.disruptor.basic.web;

import com.mq.disruptor.basic.web.service.DisruptorMqService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author shenlx
 * @description
 * @date 2026/5/11 10:13
 */
@Slf4j
@SpringBootTest
public class ApplicationTest {
    @Autowired
    private DisruptorMqService disruptorMqService;

    /**
     * 项目内部使用Disruptor做消息队列
     * @throws Exception
     */
    @Test
    public void sayHelloMqTest() throws Exception {

        for (int i=0;i<=5;i++){
            disruptorMqService.sayHelloMq(String.format("%s:消息到了，Hello world!",i));
            log.info("消息队列已发送完毕");
            //这里停止2000ms是为了确定是处理消息是异步的
            Thread.sleep(2000);
        }

    }
}