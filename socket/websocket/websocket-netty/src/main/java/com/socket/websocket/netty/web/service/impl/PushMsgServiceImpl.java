package com.socket.websocket.netty.web.service.impl;

import com.socket.websocket.netty.web.config.NettyConfig;
import com.socket.websocket.netty.web.service.PushMsgService;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author shenlx
 * @description
 * @date 2025/1/21 15:31
 */
@Service
public class PushMsgServiceImpl implements PushMsgService {
    @Override
    public void pushMsgToOne(String userId, String msg) {
        Channel channel = NettyConfig.getChannel(userId);

        if(Objects.isNull(channel)){
            throw new RuntimeException("未连接socket服务器");
        }
        channel.writeAndFlush(new TextWebSocketFrame(msg));
    }

    @Override
    public void pushToAll(String msg) {
        NettyConfig.getChannelGroup().writeAndFlush(new TextWebSocketFrame());
    }
}
