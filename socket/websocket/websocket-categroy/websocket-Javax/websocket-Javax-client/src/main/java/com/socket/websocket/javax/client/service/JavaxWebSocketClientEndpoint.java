package com.socket.websocket.javax.client.service;

import lombok.extern.slf4j.Slf4j;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;

/**
 * @author shenlx
 * @description
 * @date 2026/5/11 15:21
 */
@Slf4j
@ClientEndpoint
public class JavaxWebSocketClientEndpoint {

    private MessageHandler messageHandler;
    private int retryCount = 0;

    private final static String endpointUri = "ws://localhost:8001/websocket/types";

    private final Session session;

    public JavaxWebSocketClientEndpoint() {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            session=container.connectToServer(this, URI.create(endpointUri));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @OnOpen
    public void onOpen() {
        //连接建立
        //session
        log.info("建立连接");
    }

    @OnClose
    public void onClose( CloseReason reason) {
        //连接关闭
        log.info("断开连接");

        retryConnect();
    }

    //@OnMessage
    public void onMessage( String message) {
        //接收文本消息
        log.info("Connected to endpoint: {}", session.getBasicRemote());
        session.getAsyncRemote().sendText("hello word");
    }

    //@OnMessage
    public void onMessage(Session session, PongMessage message) throws IOException {
        //接收pong消息
        ByteBuffer source = ByteBuffer.allocate(32);
        source.put("HelloWorld\nI'm zhangsan\n Hai".getBytes());
        session.getAsyncRemote().sendPing(source);
    }

    //@OnMessage
    public void onMessage(ByteBuffer message) {
        //接收二进制消息
        ByteBuffer source = ByteBuffer.allocate(32);
        source.put("HelloWorld\nI'm zhangsan\n Hai".getBytes());
        session.getAsyncRemote().sendBinary(source);
    }

    @OnError
    public void onError(Session session, Throwable e) {
        //异常处理
    }

    public void closeConnection(){
        try{
            if(session !=null && session.isOpen()){
                session.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void retryConnect() {
        if (retryCount < 5) {
            try {
                Thread.sleep(1000); // 等待1秒后重试连接
                WebSocketContainer container = ContainerProvider.getWebSocketContainer();
                container.connectToServer(this, URI.create(endpointUri));
                retryCount++;
            } catch (Exception e) {
                System.out.println("Error reconnecting: " + e.getMessage());
                retryConnect(); // 如果连接失败，继续重试连接
            }
        } else {
            System.out.println("Max retries reached, giving up.");
        }
    }

    @OnMessage
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public void addMessageHandler(MessageHandler handler) {
        this.messageHandler = handler;
    }


}
