package com.socket.websocket.webflux.client.config;


import com.socket.websocket.webflux.client.handler.ReactiveWebSocketClientHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author shenlx
 * @description
 * @date 2025/2/28 17:52
 */
@Configuration
public class ReactiveWebSocketClient {

    @Bean
    public WebSocketClient connect() throws URISyntaxException {
        WebSocketClient client = new ReactorNettyWebSocketClient();
        WebSocketHandler handler =new ReactiveWebSocketClientHandler();
         client.execute(new URI("ws://localhost:8001/websocket"), handler).subscribe();
        return client;
    }
}
