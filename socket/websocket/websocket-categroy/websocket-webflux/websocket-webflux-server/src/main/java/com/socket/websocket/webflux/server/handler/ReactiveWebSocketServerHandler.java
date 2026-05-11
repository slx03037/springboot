package com.socket.websocket.webflux.server.handler;


import com.socket.websocket.webflux.server.utils.WebSocketSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shenlx
 * @description
 * @date 2025/2/28 13:41
 */
@Component
@Slf4j
public class ReactiveWebSocketServerHandler implements WebSocketHandler {
    public static ConcurrentHashMap<String, WebSocketSender> senderMap=new ConcurrentHashMap<>();
    @NonNull
    @Override
    public Mono<Void> handle(WebSocketSession session) {

        //        session.send(Flux.create(sink->{
//            //可以持有sink对应在任意时候调用next发送信息
//            sink.next(WebSocketMessage message);
//        })).doOnError(it->{
//            //异常处理
//        });
        String id = session.getId();

        Mono<Void> output = session.send(Flux.create(sink -> {
            senderMap.put(id, new WebSocketSender(session, sink));
        }));
        /**
         * 通过WebSocketSession#send方法来持有一个FluxSink<WebSocketMessage>来用于发送消息
         */
//        Mono<Void> send = session.send(session.receive()
//                .map(msg -> session.textMessage("receive your message:" + msg.getPayloadAsText())));

//        Mono<Void> receive = session.receive()
//                .doOnNext(it -> {
//                    //接收消息
//                })
//                .doOnError(it -> {
//                    //异常处理
//                })
//                .then();
        /**
         * 通过WebSocketSession#receive来订阅消息
         */
//        Mono<Void> receive = session.receive().doOnNext(webSocketMessage -> {
//            log.info("接收消息:{}",webSocketMessage.getPayloadAsText());
//            //session.send(Mono.just(session.textMessage("hello reactor"))).subscribe();
//        }).then();

        Mono<Void> input = session.receive()
                .map(WebSocketMessage::getPayloadAsText).map(msg -> id + ": " + msg)
                .doOnNext(System.out::println).then();

        /**
         * 通过WebSocketSession#closeStatus来订阅连接关闭事件
         * 1000 (Normal Closure): 正常关闭，表示连接完成。
         * 1001 (Going Away): 客户端或服务端主动断开（例如页面关闭）。
         * 1002 (Protocol Error): 协议错误。
         * 1003 (Unsupported Data): 不支持的数据类型。
         * 1005 NO_STATUS_CODE 未提供状态码的关闭，保留值
         * 1006 NO_CLOSE_FRAME 异常关闭 常见于网络问题或客户端断开。可以设置重连机制来保持连接的稳定性
         * 1007 BAD_DATA 收到了与消息类型不一致的数据（例如，非 UTF-8 数据）
         * 1008 POLICY_VIOLATION 收到的消息违反了服务器的策略
         * 1009 TOO_BIG_TO_PROCESS 收到的消息太大，无法处理
         * 1010 REQUIRED_EXTENSION 客户端期望服务器支持某些扩展，但服务器未提供
         * 1011 SERVER_ERROR 服务器由于内部错误无法处理请求
         * 1012 SERVICE_RESTARTED 服务端正在重启，客户端可以稍后重连
         * 1013 SERVICE_OVERLOAD 服务器繁忙
         * 1015 TLS_HANDSHAKE_FAILURE TLS 握手失败，保留值
         * 4500 SESSION_NOT_RELIABLE 会话变得不可靠，例如在超时发送消息时
         */
        @SuppressWarnings("all")
        Disposable disposable = session.closeStatus()
                .doOnError(it -> {
                    //异常处理
                    log.error("连接报错:{}",it.getMessage());
                })
                .subscribe(it -> {
                    //连接关闭
                    log.info("连接关闭:{},{}",it.getCode(),it.getReason());
                });
        return Mono.zip(output,input).then();
    }
}
