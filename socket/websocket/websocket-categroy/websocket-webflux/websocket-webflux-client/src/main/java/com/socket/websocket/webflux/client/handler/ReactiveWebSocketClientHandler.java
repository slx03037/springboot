package com.socket.websocket.webflux.client.handler;


import com.socket.websocket.webflux.client.utils.WsWrap;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author shenlx
 * @description
 * @date 2025/2/28 17:10
 */
@Component
@Slf4j
public class ReactiveWebSocketClientHandler implements WebSocketHandler {
    //private WsWrap wsWrap;
    @NonNull
    @Override
    public Mono<Void> handle(WebSocketSession session) {
        if(session.isOpen()){
            log.info("建立连接");
        }

//        String id = session.getId();
//        Mono<Void> input = session.receive()
//                .map(WebSocketMessage::getPayloadAsText).map(msg -> id + ": " + msg)
//                .doOnNext(System.out::println).then();

        Mono<Void> input = session.receive().doOnNext(webSocketMessage -> {
            log.info("接收服务端消息：{}",webSocketMessage.getPayloadAsText());
        }).then();


        Mono<Void> output = session.send(Flux.create(sink ->
                    //可以持有sink对应在任意时候调用next发送信息sink.next(WebSocketMessage);
                {
                    sink.next(session.textMessage("hello world"));
                    //wsWrap = new WsWrap(session, sink);
                    WsWrap.set(session,sink);
                }
        ));

//        session.closeStatus().doOnNext(it->{
//
//        })
        /**
         * Mono.zip() 会将多个 Mono 合并为一个新的 Mono，
         * 任何一个 Mono 产生 error 或 complete 都会导致合并后的 Mono
         * 也随之产生 error 或 complete，此时其它的 Mono 则会被执行取消操作。
         */
        return Mono.zip(input, output).then();
    }

//    @Data
//    @AllArgsConstructor
//    private static class WsWrap {
//        private WebSocketSession session;
//        private FluxSink<WebSocketMessage> sink;
//
//        public void sendText(Object obj) {
//            sink.next(session.textMessage(JsonUtils.toJson(obj)));
//        }
//    }

}
