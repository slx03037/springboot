package com.socket.websocket.javax.client.controller;

import com.socket.websocket.javax.client.service.JavaxWebSocketClientEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author shenlx
 * @description
 * @date 2026/5/11 15:21
 */
@RestController
public class ClientController {
    @GetMapping("/sendToServer")
    public void websocket1(@RequestParam("message") String message) throws IOException {
        new JavaxWebSocketClientEndpoint().sendMessage(message);
    }
}
