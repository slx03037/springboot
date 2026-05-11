package com.socket.websocket.javax.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.nio.ByteBuffer;

/**
 * @author shenlx
 * @description ServerEndpoint 注解来表示这是一个服务端点，同时可以在注解中配置路径， 这个路径可以配置成动态的，使用{}包起来就可以了
 * @date 2026/5/11 15:24
 */
@ServerEndpoint("/websocket/{type}")
@Slf4j
@Component
public class JavaxWebSocketServerEndpoint {
    /**
     * 建立连接
     * @param session
     * @param config
     * @param type
     */
    @OnOpen //@OnOpen用来标记对应的方法作为客户端连接上来之后的回调，Session就相当于和客户端的连接啦，我们可以把它缓存起来用于发送消息；通过@PathParam注解就可以获得动态路径中对应值了
    public void onOpen(Session session, EndpointConfig config, @PathParam(value="type")String type){
        log.info("id信息:{},type:{}",session.getId(),type);
    }

    /**
     * 连接关闭
     * @param session
     * @param reason
     */
    @OnClose //@OnClose用来标记对应的方法作为客户端断开连接之后的回调，我们可以在这个方法中移除对应Session的缓存，同时可以接受一个CloseReason的参数用于获取关闭原因
    public void onClose(Session session, CloseReason reason){
        log.info("{}，连接关闭的原因:{}",session.getId(),reason);
    }


    /**
     * 接收文本信息
     * @param session
     * @param message
     */
    @OnMessage //@OnMessage用来标记对应的方法作为接收到消息之后的回调，我们可以接受文本消息，二进制消息和pong消息
    public void onMessage(Session session,String message){
        log.info("接收来自{}的信息内容为:{}",session.getId(),message);
    }

    /**
     * 接收pong信息
     * @param session
     * @param message
     */
    //@OnMessage //javax.websocket库中定义了PongMessage而没有PingMessage
    public void onMessage(Session session, PongMessage message){
        log.info("接收pong信息:{}",session.getId());
    }

    //@OnMessage
    public void onMessage(Session session, ByteBuffer message){
        log.info("接收二进制消息:{}",session.getId());
    }

    @OnError//@OnError用来标记对应的方法作为抛出异常之后的回调，可以获得对应的Session和异常对象
    public void onError(Session session, Throwable e){
        log.info("消息异常:{}",session.getId());
    }
}
