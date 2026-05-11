package com.socket.websocket.webflux.web.jwt.handler;

import com.socket.websocket.webflux.web.jwt.utils.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.CloseStatus;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2025/3/3 13:46
 */
@Component
public class WebSocketAuthHandler implements WebSocketHandler {

    private final MyWebSocketHandler delegate;
    private final JwtUtil jwtUtil;

    public WebSocketAuthHandler(MyWebSocketHandler delegate, JwtUtil jwtUtil) {
        this.delegate = delegate;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        String token = getAuthToken(session);
        if (token == null || !jwtUtil.validateToken(token)) {
            return session.close(CloseStatus.NOT_ACCEPTABLE.withReason("Invalid JWT Token"));
        }
        return delegate.handle(session);
    }

    private String getAuthToken(WebSocketSession session) {
        List<String> authHeader = session.getHandshakeInfo().getHeaders().get("Authorization");
        return (authHeader != null && !authHeader.isEmpty()) ? authHeader.get(0).replace("Bearer ", "") : null;
    }
}