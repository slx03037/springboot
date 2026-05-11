package com.socket.websocket.web.simple.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author shenlx
 * @description
 * @date 2024/9/24 9:56
 */
@Configuration
public class WebSocketConfig {

    /**
     * 注入ServerEndpointExporter ，该bean会自动注册使用@@ServerEndpoint注解申明的websocket endpoint
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
