package com.socket.websocket.webfulx.web.handler;

import com.socket.websocket.webfulx.web.annotation.WebSocketMapping;
import com.socket.websocket.webfulx.web.manager.UserTokenManager;
import com.socket.websocket.webfulx.web.utils.WebSocketSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.socket.HandshakeInfo;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shenlx
 * @description
 * @date 2025/3/2 0:17
 */

@Component
@Slf4j
@WebSocketMapping("/wechat/notice")
public class WeChatNoticeHandle implements WebSocketHandler {

    /**
     *  所有websocket连接管理容器
     **/
    private final ConcurrentHashMap<String, WebSocketSender> senderMap;

    /**
     *  平台Token管理服务
     **/
    private final UserTokenManager userTokenManager;

    public WeChatNoticeHandle(ConcurrentHashMap<String, WebSocketSender> senderMap, UserTokenManager userTokenManager) {
        this.senderMap = senderMap;
        this.userTokenManager = userTokenManager;
    }

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        HandshakeInfo handshakeInfo = session.getHandshakeInfo();
        //解析URL上的所有参数
        //Map<String, String> queryMap = JetHttpUtils.getQueryMap(handshakeInfo.getUri().getQuery());
        //当前用户登录Token
        String token;

        //解析读取请求体上的token信息
        String query = session.getHandshakeInfo().getUri().getQuery();
        if (StringUtils.hasText(query) && query.contains(":X_Access_Token")) {
            //token = HttpUtils.parseEncodedUrlParams(query).get(":X_Access_Token");
        } else if (session.getHandshakeInfo().getHeaders().containsKey("X-Access-Token")) {
            token = session
                    .getHandshakeInfo()
                    .getHeaders()
                    .getFirst("X-Access-Token");
        } else {
            String paths = session.getHandshakeInfo().getUri().getPath();
            String[] path = paths.split("[/]");
            if (path.length == 0) {
                return Mono.empty();
            }
            token = path[path.length - 1];
        }

        //根据用户token获取用户信息
//        return userTokenManager
//                .getByToken(token)
//                .switchIfEmpty(Mono.defer(() -> {
//                    //客户端发送给服务端的消息处理
//                    Mono<Void> inputServer = session
//                            .receive()
//                            .map(WebSocketMessage::getPayloadAsText)
//                            .map(message -> {
//                                log.info("【非平台连接】websocket连接服务，收到来自客户端的消息：{}",message);
//                                return message;
//                            })
//                            .then();
//
//                    //服务端给客户端推送消息
//                    Mono<Void> outputClient = session
//                            .send(Flux.create(sink -> senderMap
//                                    .put(queryMap.getOrDefault("userId","defaultId"),
//                                            new WebSocketSender(session, sink))));
//                    return Mono.zip(inputServer, outputClient)
//                            .then(Mono.empty());
//                }))
//                .map(UserToken::getUserId)
//                .flatMap(userId -> {
//                    //客户端发送给服务端的消息处理
//                    Mono<Void> inputServer = session
//                            .receive()
//                            .map(WebSocketMessage::getPayloadAsText)
//                            .map(message -> {
//                                log.info("【微信公众号】websocket连接服务，收到来自客户端用户【{}】的消息：{}",userId,message);
//                                return message;
//                            })
//                            .then();
//
//                    //服务端给客户端推送消息
//                    Mono<Void> outputClient = session
//                            .send(Flux.create(sink -> senderMap.put(token, new WebSocketSender(session, sink))));
//                    return Mono.zip(inputServer, outputClient)
//                            .then();
//                });
        return Mono.empty();
    }
}