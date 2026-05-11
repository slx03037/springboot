package com.socket.websocket.webflux.server.controller;

import com.socket.websocket.webflux.server.handler.ReactiveWebSocketServerHandler;
import com.socket.websocket.webflux.server.utils.WebSocketSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shenlx
 * @description
 * @date 2025/2/28 21:00
 */
@RestController
public class WebSocketServerController {

    /**
     * 注意 url不要与websocket的设置路径相同
     * @return
     */
    @GetMapping("/websocket2/server")
    public Mono<Void> testMethod(@RequestParam String message){
        ConcurrentHashMap<String, WebSocketSender> senderMap = ReactiveWebSocketServerHandler.senderMap;
        senderMap.keySet().stream().forEach(key->{
            WebSocketSender webSocketSender = senderMap.get(key);
            webSocketSender.sendData(message);
        });
        return Mono.empty();
    }
}
