package com.socket.websocket.simple.web.node01.server;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2025/2/27 10:12
 */
@Slf4j
public class myWebSocketServer extends WebSocketServer {

    // 定义有参构造器，用于服务端websocket的创建
    public myWebSocketServer(String ip,Integer port){
        super(new InetSocketAddress(ip,port));
    }

    // 定义一个集合用来存储和当前websocket服务器保持连接的websocket客户端
    List<WebSocket> onLine = new ArrayList<>();


    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        /**
         * onOpen方法会在客户端websocket连接上当前服务端时调用，参数webSocket代表的就是客户端的websocket对象，
         * 可以通过该对象发送消息给客户端，也可以通过该对象获取客户端的ip地址等信息。
         */

        log.info("有新的websocket客户端连接：{}",webSocket.getRemoteSocketAddress().getHostName()+":"+webSocket.getRemoteSocketAddress().getPort());
        // 当有客户端连接将其加入onLine集合中
        onLine.add(webSocket);

    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        /**
         * onClose方法是当客户端断开连接时调用
         */

        log.info("websocket客户端断开连接：{}",webSocket.getRemoteSocketAddress().getHostName()+":"+webSocket.getRemoteSocketAddress().getPort());
        // 当有客户端断开连接将其从onLine集合中移除
        onLine.remove(webSocket);

    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        /**
         * onMessage方法则是客户端向服务端发送消息时调用，
         * 其中webSocket参数表明发送消息的客户端，字符串s表示所发送的消息
         */
        log.info("收到客户端:{}",webSocket.getRemoteSocketAddress().getHostName()+":"+webSocket.getRemoteSocketAddress().getPort()+" 发送过来的消息："+s);
        // 向客户端回馈消息
        webSocket.send("收到");
        // 亦可以群发消息
        for (WebSocket socket : onLine) {
            socket.send(webSocket.getRemoteSocketAddress().getHostName()+":"+webSocket.getRemoteSocketAddress().getPort()+" 群发消息："+s);
        }
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {
        /**
         * onError方法是当客户端和当前服务端通信出现异常时调用
         */
        log.info("和客户端:{} :{} 通信发生异常",webSocket.getRemoteSocketAddress().getHostName()
                ,webSocket.getRemoteSocketAddress().getPort());

    }

    @Override
    public void onStart() {
        /**
         * onStart方法则是服务端开启时调用
         */
        log.info("websocket服务端已启动");
    }

}
