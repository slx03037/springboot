package com.socket.websocket.webfulx.web.controller;


import com.socket.websocket.webfulx.web.utils.WebSocketSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shenlx
 * @description websocket连接与消息推送测试
 * @date 2025/3/2 0:18
 */
@RestController
//@Authorize(ignore = true)
@RequestMapping("/websocket")
public class WebSocketTestController {

    @Autowired
    private ConcurrentHashMap<String, WebSocketSender> senderMap;

    /**
     * 用途:测试websocket消息推送
     * @author liaoyibin
     * @date 14:20 2023/2/10
     * @params [userId, data]
     * @param userId 用户ID
     * @param data 推送数据
     **/
    @RequestMapping("/send")
    public Mono<Object> sendMessage(@RequestParam String userId, @RequestParam String data) {
        WebSocketSender sender = senderMap.get(userId);
        if (Optional.ofNullable(sender).isPresent()) {
            sender.sendData(data);
            String message = String.format("Message '%s' sent to connection: %s.", data, userId);
            return Mono.just(message);
        }
        return Mono.just(String.format("Connection of id '%s' doesn't exist", userId));
    }

}
