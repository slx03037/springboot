package com.socket.websocket.simple.web.node01;


import com.socket.websocket.simple.web.node01.client.myWebSocketClient;
import com.socket.websocket.simple.web.node01.server.myWebSocketServer;

import java.net.URI;

/**
 * @author shenlx
 * @description
 * @date 2025/2/27 10:21
 */
public class WebSocketTest {
    public static void main(String[] args) throws Exception {
        // 创建服务端websocket对象
        myWebSocketServer server = new myWebSocketServer("127.0.0.1",8899);
        // 启动服务端websocket
        server.start();

        // 循环启动多个客户端连接服务端
        for (int i = 0;i<4;i++) {
            // 连接服务端websocket的地址
            URI uri = new URI("ws://127.0.0.1:8899");
            // 创建客户端websocket对象
            myWebSocketClient client = new myWebSocketClient(uri);
            // 阻塞式连接
            client.connectBlocking();
            // 向服务端发送消息
            client.send("我是客户端"+i);
        }
    }
}
