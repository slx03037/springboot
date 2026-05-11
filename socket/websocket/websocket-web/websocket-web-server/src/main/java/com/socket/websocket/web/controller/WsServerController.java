package com.socket.websocket.web.controller;


import com.socket.websocket.web.server.WsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenlx
 * @description
 * @date 2025/2/26 17:12
 */
@RestController
public class WsServerController {
    @Autowired
    WsServer wsServer;

    /**
     * 服务端发消息给客户端
     * @param message 消息
     */
    @GetMapping("/send2client")
    public void send2Client(@RequestParam("message") String message){
//        wsServer.sendMessageToAll("this is a test for server to client");
        wsServer.sendMessageToAll(message);
    }

}
