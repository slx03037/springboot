package com.netty.basic.web.netty.agreement.exmaple.demo2;

import com.netty.basic.web.netty.agreement.login.auth.MessageType;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * @author shenlx
 * @description
 * @date 2024/4/3 15:36
 */

public class HeartBeatRespHandler extends ChannelInboundHandlerAdapter {
    private static final Log LOGGER = LogFactory.getLog(HeartBeatRespHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        NettyMessage message = (NettyMessage) msg;

        // 判断是否 是心跳检测消息
        if (message.getHeader() != null && message.getHeader().getType() ==
                MessageType.HEARTBEAT_REQ.getValue()) {

            LOGGER.info("Receive client heart beat message : --->  " +message);
            NettyMessage heartBeat = buildHeatBeat();
            LOGGER.info("Send heart beat response message to client : ---> " +heartBeat);
            ctx.writeAndFlush(heartBeat);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    // 生成心跳检测消息
    private NettyMessage buildHeatBeat() {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.HEARTBEAT_RESP.value());
        message.setHeader(header);
        return message;
    }
}
