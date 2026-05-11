package com.socket.websocket.javax.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author shenlx
 * @description
 * @date 2026/5/11 15:23
 */
@Configuration(proxyBeanMethods = false)
public class JavaxWebSocketConfiguration {

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
