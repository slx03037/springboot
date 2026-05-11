package com.socket.websocket.interceptor.web.config;


import com.socket.websocket.interceptor.web.handler.HandShakeInterceptorHandler;
import com.socket.websocket.interceptor.web.handler.MyWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author shenlx
 * @description
 * @date 2025/2/27 15:02
 */
@Configuration//单例模式bean
@EnableWebSocket//启动webSocket服务
public class WebSocketConfig implements WebMvcConfigurer, WebSocketConfigurer {

    @Autowired
    private HandShakeInterceptorHandler handler;

    @Autowired
    private MyWebSocketHandler webSocketHandler;


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //-------允许跨域访问WebSocket-----------
        String[]allowsOrigins={"*"};//允许连接的域，只能以http或者https开头

        //7设置websocket服务器地址
        registry.addHandler(webSocketHandler,"/websocket")
                .addInterceptors(handler)
                .setAllowedOrigins(allowsOrigins);
    }
}
