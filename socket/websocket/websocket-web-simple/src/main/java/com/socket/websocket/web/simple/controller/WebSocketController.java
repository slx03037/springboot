package com.socket.websocket.web.simple.controller;


import com.socket.websocket.web.simple.server.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.io.IOException;

/**
 * @author shenlx
 * @description
 * @date 2024/9/24 10:12
 */
@RestController
@RequestMapping("/open/socket")
public class WebSocketController {

    @Value("${mySocket.myPwd}")
    private String myPwd;

    @Autowired
    private WebSocketServer webSocketServer;

    @GetMapping(value = "/onReceive")
    public void onReceive(@PathParam("id") String id, @PathParam("pwd")String pwd) throws IOException {
        if(pwd.equals(myPwd)) {  //密码校验一致（这里举例，实际开发还要有个密码加密的校验的），则进行群发
            webSocketServer.broadCastInfo(id);
        }
    }
}
