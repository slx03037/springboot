package com.socket.websocket.socketio.server.controller;


import com.socket.websocket.socketio.server.service.ISocketIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenlx
 * @description
 * @date 2025/3/3 11:30
 */
@RestController
@RequestMapping("/api/socket/io")
public class SocketIOController {
    @Autowired
    private ISocketIOService socketIOService;

    @PostMapping(value = "/pushMessageToUser")

    public Object pushMessageToUser(@RequestParam String userId, @RequestParam String msgContent) {
        socketIOService.pushMessageToUser(userId, msgContent);
        return "success";
    }


}
