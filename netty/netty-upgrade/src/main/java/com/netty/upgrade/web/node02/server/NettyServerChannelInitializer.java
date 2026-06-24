package com.netty.upgrade.web.node02.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author shenlx
 * @description
 * @date 2024/11/11 14:52
 */
public class NettyServerChannelInitializer  extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //接收消息格式,使用自定义解析数据格式
//        pipeline.addLast("decoder",new MyDecoder());
        //发送消息格式，使用自定义解析数据格式
//        pipeline.addLast("encoder",new MyEncoder());
        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
        //针对客户端，如果在1分钟时没有想服务端发送写心跳(ALL)，则主动断开
        //如果是读空闲或者写空闲，不处理,这里根据自己业务考虑使用
        pipeline.addLast(new IdleStateHandler(0,0,90, TimeUnit.SECONDS));
        //自定义的空闲检测
        pipeline.addLast(new NettyServerHandler());
    }
}
