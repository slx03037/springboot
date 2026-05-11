package com.mq.disruptor.basic.web.handler;

import com.lmax.disruptor.EventHandler;
import com.mq.disruptor.basic.web.model.MessageModel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shenlx
 * @description
 * @date 2026/5/11 10:10
 */
@Slf4j
public class HelloEventHandler implements EventHandler<MessageModel> {
    @Override
    public void onEvent(MessageModel event, long sequence, boolean endOfBatch) {
        try {
            //这里停止1000ms是为了确定消费消息是异步的
            Thread.sleep(1000);
            log.info("消费者处理消息开始");
            if (event != null) {
                log.info("消费者消费的信息是：{}", event);
            }
        } catch (Exception e) {
            log.info("消费者处理消息失败");
        }
        log.info("消费者处理消息结束");
    }
}

