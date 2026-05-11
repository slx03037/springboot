package com.socket.websocket.interceptor.web.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author shenlx
 * @description
 * @date 2025/2/27 14:51
 */
@Slf4j
@Component
public class HandShakeInterceptorHandler implements HandshakeInterceptor {
    /**
     * 握手对象
     * @param request the current request
     * @param response the current response
     * @param handler the target WebSocket handler
     * @param map the attributes from the HTTP handshake to associate with the WebSocket
     * session; the provided attributes are copied, the original map is not used.
     * @return
     * @throws Exception
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler handler, Map<String, Object> map) throws Exception {
        ServletServerHttpRequest servletServerHttpRequest = (ServletServerHttpRequest) request;
        //得到http协议的请求对象
        HttpServletRequest servletRequest = servletServerHttpRequest.getServletRequest();
        HttpSession session = servletRequest.getSession(true);
        log.info("握手拦截器 param:{},token:{}"
                ,servletRequest.getParameter("param")
                ,servletRequest.getParameter("token"));
        //数据中转 可以把http协议的会话对象数据中转到ws协议的会话对象种
        map.put("param" ,servletRequest.getParameter("param"));

        //非前后端分离架构:把HttpSession种的数据中转到WebSocketSession种
        if(session.getAttribute("user") !=null){
            map.put("user",session.getAttribute("user"));
            //如果是前端分离架构:把Http协议种的token令牌中转到ws协议的WebSocketSession中
            map.put("token",servletRequest.getParameter("token"));
            return true;
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        log.info("握手之后");
    }
}
