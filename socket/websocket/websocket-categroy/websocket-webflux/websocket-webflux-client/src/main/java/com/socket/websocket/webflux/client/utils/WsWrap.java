package com.socket.websocket.webflux.client.utils;

import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.FluxSink;

/**
 * @author shenlx
 * @description
 * @date 2025/2/28 21:42
 */

public class WsWrap {
    private static WebSocketSession session;
    private static FluxSink<WebSocketMessage> sink;

    public static void sendText(Object obj) {
        sink.next(session.textMessage(JsonUtils.toJson(obj)));
    }

    public static void set(WebSocketSession session,FluxSink<WebSocketMessage> sink) {
        WsWrap.session = session;
        WsWrap.sink = sink;
    }
}
