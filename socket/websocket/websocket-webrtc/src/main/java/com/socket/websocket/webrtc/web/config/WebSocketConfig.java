package com.socket.websocket.webrtc.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author shenlx
 * @description
 * @date 2025/3/2 0:32
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig{
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }


}