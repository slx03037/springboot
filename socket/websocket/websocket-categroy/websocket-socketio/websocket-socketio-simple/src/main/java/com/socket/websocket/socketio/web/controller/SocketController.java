package com.socket.websocket.socketio.web.controller;

import com.corundumstudio.socketio.SocketIOClient;
import com.socket.websocket.socketio.web.cache.ClientCache;
import com.socket.websocket.socketio.web.pojo.MessageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author shenlx
 * @description
 * @date 2025/3/3 12:39
 */
@RestController
@RequestMapping("/push")
public class SocketController {
    @Resource
    private ClientCache clientCache;
    @GetMapping("/user/{userId}")
    public String pushTuUser(@PathVariable("userId") String userId){
        HashMap<UUID, SocketIOClient> userClient = clientCache.getUserClient(userId);
        userClient.forEach((uuid, socketIOClient) -> {
            //向客户端推送消息
            socketIOClient.sendEvent("chatevent",new MessageInfo("管理员","向客户段发送的消息"));
        });
        return "success";
    }
}
