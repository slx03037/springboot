package com.socket.websocket.webflux.server.config;//package com.xinwen.websokcet.webflux.server.config;
//
//import com.xinwen.websokcet.webflux.server.handler.ReactiveWebSocketServerHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
//import org.springframework.web.reactive.socket.WebSocketHandler;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author shenlx
// * @description
// * @date 2025/2/28 15:43
// */
//@Component
//public class ReactiveWebSocketServerHandlerMapping extends SimpleUrlHandlerMapping {
//    @Autowired
//    private ReactiveWebSocketServerHandler reactiveWebSocketServerHandler;
//
//    public ReactiveWebSocketServerHandlerMapping(){
//        Map<String, WebSocketHandler> map=new HashMap<>();
//
//        map.put("/websocket/**",reactiveWebSocketServerHandler);
//
//        setUrlMap(map);
//        /**
//         * HandlerMapping需要设置order，如果不设置，默认为Ordered.LOWEST_PRECEDENCE，会导致这个HandlerMapping被放在最后，
//         * 当有客户端连接上来时会被其他的HandlerMapping优先匹配上而连接失败
//         */
//        setOrder(100);
//    }
//}