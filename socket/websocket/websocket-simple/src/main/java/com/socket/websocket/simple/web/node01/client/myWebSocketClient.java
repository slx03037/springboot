package com.socket.websocket.simple.web.node01.client;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

/**
 * @author shenlx
 * @description
 * @date 2025/2/27 10:20
 */
@Slf4j
public class myWebSocketClient extends WebSocketClient {
    public myWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        log.info("websocket成功连接");
    }

    @Override
    public void onMessage(String s) {
        log.info("收到服务端发送的消息：{}",s);

    }

    @Override
    public void onClose(int i, String s, boolean b) {
        log.info("已经和服务端断开连接");
    }

    @Override
    public void onError(Exception e) {
        log.info("和服务端通信发送异常");
    }
}

