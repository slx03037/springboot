package com.socket.websocket.webmvc.server.config;


import com.socket.websocket.webmvc.server.handler.ServletWebSocketServerHandler;
import com.socket.websocket.webmvc.server.interceptor.ServletWebSocketHandshakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

import java.io.IOException;

/**
 * @author shenlx
 * @description
 * @date 2025/2/27 16:41
 */
@Configuration//单例模式bean
@EnableWebSocket//启动webSocket服务
public class ServletWebSocketServerConfigurer implements WebSocketConfigurer {

    @Autowired
    private ServletWebSocketServerHandler webSocketServerHandler;

    @Autowired
    private ServletWebSocketHandshakeInterceptor webSocketHandshakeInterceptor;

//    @Override
//    public void registerWebSocketHandlers(@NonNull WebSocketHandlerRegistry registry) {
//        registry
//                //添加处理器到对应的路径
//                .addHandler(new ServletWebSocketServerHandler(), "/websocket")//注册Handler
//                .setAllowedOrigins("*");
//    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //-------允许跨域访问WebSocket-----------
 //       String[] allowsOrigins = {"*"};//允许连接的域，只能以http或者https开头
//        if (registry instanceof ServletWebSocketHandlerRegistry) {
//            //替换UrlPathHelper
//            ((ServletWebSocketHandlerRegistry) registry)
//                    .setUrlPathHelper(new PrefixUrlPathHelper("/websocket"));
//        }

//        registry
//                //.addHandler(webSocketServerHandler, "websocket/**") //添加处理器到对应的路径
//                .addHandler(webSocketServerHandler, "websocket")
//                .addInterceptors(webSocketHandshakeInterceptor)//添加握手拦截器
//                .setAllowedOrigins(allowsOrigins);

        //注册通道
        registry.addHandler(webSocketServerHandler, "/ws-service").setAllowedOrigins("*").addInterceptors(webSocketHandshakeInterceptor);
        // withSockJS() 方法声明我们想要使用 SockJS 功能，如果WebSocket不可用的话，会使用 SockJS；
        registry.addHandler(webSocketServerHandler, "/sockjs/ws-service").setAllowedOrigins("*").addInterceptors(webSocketHandshakeInterceptor).withSockJS();
    }

    /**
     * 配置消息处理头
     * @return
     * @throws IOException
     */
//    @Bean
//    public WebSocketHandler myHandler() throws IOException {
//        return new ServletWebSocketServerHandler();
//    }
//
//    //websocket拦截器
//    @Bean
//    public ServletWebSocketHandshakeInterceptor myInterceptor(){
//        return new ServletWebSocketHandshakeInterceptor();
//    }

    /**
     * 配置缓冲区的大小等配置信息
     * @return
     */
    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(8192);
        container.setMaxBinaryMessageBufferSize(8192);
        return container;
    }

}
