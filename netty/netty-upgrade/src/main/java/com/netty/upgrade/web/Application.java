package com.netty.upgrade.web;

import com.netty.upgrade.web.node02.server.NettyServer;
import com.netty.upgrade.web.node02.config.SocketProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;

import javax.annotation.Resource;
import java.net.InetSocketAddress;

/**
 * @author shenlx
 * @description
 * @date 2024/11/11 14:50
 */
@SpringBootApplication
public class Application implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.setApplicationStartup(new BufferingApplicationStartup(2048));
        application.run(args);
    }

    @Resource
    private NettyServer nettyServer;

    @Resource
    private SocketProperties socketProperties;

    @Override
    public void run(String... args) {
        InetSocketAddress address = new InetSocketAddress(socketProperties.getHost(),socketProperties.getPort());
        nettyServer.start(address);
    }

}
