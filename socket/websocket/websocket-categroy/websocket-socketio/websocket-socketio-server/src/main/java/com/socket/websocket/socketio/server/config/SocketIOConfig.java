package com.socket.websocket.socketio.server.config;

import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shenlx
 * @description
 * @date 2025/3/3 11:24
 */
@Configuration
public class SocketIOConfig {

    @Value("${socket-io.host}")
    private String host;

    @Value("${socket-io.port}")
    private Integer port;

    @Value("${socket-io.bossCount}")
    private int bossCount;

    @Value("${socket-io.workCount}")
    private int workCount;

    @Value("${socket-io.allowCustomRequests}")
    private boolean allowCustomRequests;

    @Value("${socket-io.upgradeTimeout}")
    private int upgradeTimeout;

    @Value("${socket-io.pingTimeout}")
    private int pingTimeout;

    @Value("${socket-io.pingInterval}")
    private int pingInterval;

    @Bean
    public SocketIOServer socketIOServer() {
        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setTcpNoDelay(true);
        socketConfig.setSoLinger(0);
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setSocketConfig(socketConfig);
        config.setHostname(host);
        config.setPort(port);
        config.setBossThreads(bossCount);
        config.setWorkerThreads(workCount);
        config.setAllowCustomRequests(allowCustomRequests);
        config.setUpgradeTimeout(upgradeTimeout);
        config.setPingTimeout(pingTimeout);
        config.setPingInterval(pingInterval);
        return new SocketIOServer(config);
    }

}