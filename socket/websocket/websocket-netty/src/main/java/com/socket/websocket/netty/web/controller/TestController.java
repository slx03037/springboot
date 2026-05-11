package com.socket.websocket.netty.web.controller;

import com.socket.websocket.netty.web.service.PushMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenlx
 * @description
 * @date 2025/1/21 15:36
 */
@RestController
@RequestMapping("/push")
public class TestController {

    @Autowired
    PushMsgService pushMsgService;

    @GetMapping("/{uid}")
    public void pushOne(@PathVariable String uid){
        pushMsgService.pushMsgToOne(uid,"hello");
    }
}
