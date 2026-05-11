package com.socket.websocket.netty.web.handler;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.socket.websocket.netty.web.config.NettyConfig;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * @author shenlx
 * @description
 * @date 2025/1/21 14:53
 */
@Component
@ChannelHandler.Sharable
@Slf4j
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {


    @Override
    public void handlerAdded(ChannelHandlerContext ctx)throws Exception{
        log.info("有新的客户端链接:[{}]",ctx.channel().id().asLongText());
        //添加到channelGroup 通道组里
        NettyConfig.getChannelGroup().add(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        log.info("服务器收到消息：{}",msg.text());

        //获取用户ID,关联channel
        JSONObject jsonObject = JSONUtil.parseObj(msg.text());
        String uid = jsonObject.getStr("uid");
        NettyConfig.getChannelMap().put(uid, ctx.channel());

        /**
         * 将用户的ID作为自定义属性加入到channel中，方便随时可以Channel中获取用户ID
         */
        AttributeKey<String> key = AttributeKey.valueOf("userId");
        ctx.channel().attr(key).setIfAbsent(uid);

        /**
         * 回复消息
         */
        ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器收到消息。。。"));
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx)throws Exception{
        log.info("用户下线：{}",ctx.channel().id().asLongText());

        //删除通道
        NettyConfig.getChannelGroup().remove(ctx.channel());
        removeUserId(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        log.info("异常:{}",cause.getMessage());

        //删除通道
        NettyConfig.getChannelGroup().remove(ctx.channel());
        removeUserId(ctx);
        ctx.close();
    }

    private void removeUserId(ChannelHandlerContext ctx){
        AttributeKey<String> key = AttributeKey.valueOf("userId");
        String userId = ctx.channel().attr(key).get();
        NettyConfig.getChannelMap().remove(userId);
    }
}
