package com.socket.websocket.webmvc.client;


import com.socket.websocket.webmvc.client.handler.ServletWebSocketClientHandler;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import java.io.IOException;

/**
 * @author shenlx
 * @description
 * @date 2025/2/27 17:03
 */
@SpringBootTest
public class ApplicationTest {

//    @Autowired
//    private ServletWebSocketClientHandler handler;

    @Test
    public void testMethod() throws IOException {
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketHandler handler = new ServletWebSocketClientHandler();
        WebSocketConnectionManager manager = new WebSocketConnectionManager(client, handler, "ws://localhost:8001/websocket");
        manager.start();
        System.in.read();
    }
}
