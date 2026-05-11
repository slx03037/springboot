package com.socket.websocket.netty.web.server;


import com.socket.websocket.netty.web.handler.ProjectInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;
import java.util.concurrent.CompletableFuture;

/**
 * @author shenlx
 * @description
 * @date 2025/1/21 14:43
 */
@Component
@Slf4j
public class NettyServer {

    @Value("${webSocket.netty.port}")
    private int port;

    EventLoopGroup bossGroup;
    EventLoopGroup workGroup;

    @Autowired
    ProjectInitializer projectInitializer;

    @PostConstruct
    public void start(){
        log.info("start server");
        CompletableFuture.runAsync(()->{
            bossGroup = new NioEventLoopGroup();
            workGroup = new NioEventLoopGroup();

            ServerBootstrap bootstrap = new ServerBootstrap();
            //bossGroup服务客户端的tcp连接请求，workGroup负责与客户端之间的读写操作
            bootstrap.group(bossGroup,workGroup);

            //设置NIO类型的channel
            bootstrap.channel(NioServerSocketChannel.class);

            //设置监听端口
            bootstrap.localAddress(new InetSocketAddress(port));

            //设置管道
            bootstrap.childHandler(projectInitializer);

            //配置完成，开始绑定sever，通过调用sync同步方法阻塞直到绑定成功
            ChannelFuture channelFuture=null;

            try{
                channelFuture=  bootstrap.bind().sync();
                log.info("server is started and listen on:{}",channelFuture.channel().localAddress());
                //对关闭通道进行监听
                channelFuture.channel().closeFuture().sync();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }


    /**
     * 释放资源
     */
    @PreDestroy
    public void destroy()throws InterruptedException{
        if(bossGroup !=null){
            bossGroup.shutdownGracefully().sync();
        }

        if(workGroup !=null){
            workGroup.shutdownGracefully().sync();
        }
    }
}

