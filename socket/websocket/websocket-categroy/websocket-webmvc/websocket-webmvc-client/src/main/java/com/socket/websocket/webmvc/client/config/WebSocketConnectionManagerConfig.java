package com.socket.websocket.webmvc.client.config;


import com.socket.websocket.webmvc.client.handler.ServletWebSocketClientHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import javax.servlet.annotation.WebListener;

/**
 * @author shenlx
 * @description spring容器初始化监听，初始化完成后执行websocket连接
 * @date 2025/2/27 17:29
 */
@Configuration
@WebListener
public class WebSocketConnectionManagerConfig implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ServletWebSocketClientHandler handler;
    @Bean(name = "wsCloudConnectionManager")
    public WebSocketConnectionManager wsCloudConnectionManager() {
        StandardWebSocketClient webSocketClient = new StandardWebSocketClient();
        WebSocketConnectionManager manager = new WebSocketConnectionManager(webSocketClient, handler,
                "ws://localhost:8001/ws-service?shopId=001&appId=002");//?shopId=001&appId=002
        manager.setAutoStartup(true);
        return manager;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        System.out.println("准备进行中");
    }

}
