package com.mq.disruptor.basic.web.factory;

import com.lmax.disruptor.EventFactory;
import com.mq.disruptor.basic.web.model.MessageModel;

/**
 * @author shenlx
 * @description
 * @date 2026/5/11 10:10
 */
public class HelloEventFactory implements EventFactory<MessageModel> {
    @Override
    public MessageModel newInstance() {
        return new MessageModel();
    }
}
