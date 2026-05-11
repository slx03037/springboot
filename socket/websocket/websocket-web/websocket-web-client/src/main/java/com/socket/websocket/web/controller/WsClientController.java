package com.socket.websocket.web.controller;


import org.java_websocket.client.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenlx
 * @description
 * @date 2025/2/26 17:16
 */
@RestController
public class WsClientController {
    @Autowired
    WebSocketClient wsClient;


    /**
     * 客户端发消息给服务端
     * @param message
     */
    @GetMapping("/send2server")
    public void websocket(@RequestParam("message") String message){
        wsClient.send(message);
    }


}

