package com.socket.websocket.webfulx.web.config;


import com.socket.websocket.webfulx.web.handler.WebSocketMappingHandleMapping;
import com.socket.websocket.webfulx.web.utils.WebSocketSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shenlx
 * @description 通用websocket连接服务
 * @date 2025/3/2 0:16
 */
@Configuration
@Slf4j
public class CommonWebSocketConfiguration {

    @Bean
    public ConcurrentHashMap<String, WebSocketSender> senderMap() {
        return new ConcurrentHashMap<String, WebSocketSender>();
    }

    @Bean
    public HandlerMapping webSocketMapping() {
        return new WebSocketMappingHandleMapping();
    }

    @Bean
    public WebSocketHandlerAdapter handlerAdapter() {
        return new WebSocketHandlerAdapter();
    }
}

