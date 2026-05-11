package com.socket.websocket.webmvc.client.controller;


import com.socket.websocket.webmvc.client.handler.ServletWebSocketClientHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * @author shenlx
 * @description
 * @date 2025/2/27 21:46
 */
@RestController
public class TestController {
    @Autowired
    ServletWebSocketClientHandler webSocketClientHandler;

    @GetMapping(value = "/info")
    public String info(@PathParam("message")String message) throws Exception {
        webSocketClientHandler.sendMessage(message);
        return null;
    }
}
