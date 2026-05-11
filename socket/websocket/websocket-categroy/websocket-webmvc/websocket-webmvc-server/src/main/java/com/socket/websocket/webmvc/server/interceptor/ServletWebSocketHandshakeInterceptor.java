package com.socket.websocket.webmvc.server.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author shenlx
 * @description 拦截器
 * @date 2025/2/27 16:42
 */
@Component
@Slf4j
public class ServletWebSocketHandshakeInterceptor extends HttpSessionHandshakeInterceptor  implements HandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        //握手之前
        if (request instanceof ServletServerHttpRequest) {
            //解决The extension [x-webkit-deflate-frame] is not supported问题
            if(request.getHeaders().containsKey("Sec-WebSocket-Extensions")) {
                request.getHeaders().set("Sec-WebSocket-Extensions", "permessage-deflate");
            }
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;

            HttpSession session = servletRequest.getServletRequest().getSession();
            if (session == null) {
                return false;
            }
            //使用user区分WebSocketHandler，以便定向发送消息
            HttpServletRequest req = servletRequest.getServletRequest();
            String xShopId = req.getParameter("shopId");
            String xAppId = req.getParameter("appId");

            if (org.springframework.util.StringUtils.isEmpty(xAppId) || org.springframework.util.StringUtils.isEmpty(xShopId)) {
                return false;
            }
            attributes.put("shopId", xShopId);
            return super.beforeHandshake(request, response, wsHandler, attributes);
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        //握手之后
        super.afterHandshake(request, response, wsHandler, exception);
    }

//    private boolean requestIsValid(String url){
//        //在这里可以写上具体的鉴权逻辑
//        boolean isvalid = false;
//        if(StringUtils.isNotEmpty(url)
//                && url.startsWith("/netgate/")
//                && url.split("/").length==6){
//            isvalid = true;
//        }
//        return isvalid;
//    }
//
//    private String[] getParams(String url){
//        url = url.replace("/netgate/","");
//        return url.split("/");
//    }
}
