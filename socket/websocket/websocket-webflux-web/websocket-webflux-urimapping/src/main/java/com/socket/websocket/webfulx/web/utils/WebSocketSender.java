package com.socket.websocket.webfulx.web.utils;

import lombok.Getter;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.FluxSink;

/**
 * @author shenlx
 * @description websocket发送助手类
 * @date 2025/3/2 0:16
 */
@Getter
public class WebSocketSender {

    /**
     *  待操作websocket连接会话
     **/
    private final WebSocketSession session;

    /**
     *  websocket响应堆栈操作API
     **/
    private final FluxSink<WebSocketMessage> sink;

    public WebSocketSender(WebSocketSession session, FluxSink<WebSocketMessage> sink) {
        this.session = session;
        this.sink = sink;
    }

    /**
     * 用途:发送消息
     * @author liaoyibin
     * @date 11:19 2023/2/10
     * @params [data]
     * @param data 待发送数据
     **/
    public void sendData(String data) {
        sink.next(session.textMessage(data));
    }
}
