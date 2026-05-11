package com.socket.websocket.webflux.client.controller;


import com.socket.websocket.webflux.client.utils.WsWrap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author shenlx
 * @description
 * @date 2025/2/28 21:37
 */
@RestController
public class WebSocketClientController {


    /**
     * 注意 url不要与websocket的设置路径相同
     * @return
     */
    @GetMapping("/websocket2/client")
    public Mono<Void> testMethod(@RequestParam String message){
        WsWrap.sendText(message);
        return Mono.empty();
    }
}
