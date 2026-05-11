package com.socket.websocket.webmvc.server.controller;


import com.socket.websocket.webmvc.server.handler.ServletWebSocketServerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenlx
 * @description
 * @date 2025/2/27 21:51
 */
@RestController
public class TestController {
    @Autowired
    ServletWebSocketServerHandler webSocketServerHandler;

    @GetMapping(value = "/info")
    public String info() throws Exception {
        webSocketServerHandler.sendMessage("001","服务端与客户端信息通讯");
        return "";
    }
}
