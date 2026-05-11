package com.socket.websocket.socketio.web.config;

import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import com.socket.websocket.socketio.web.handler.SocketIOHandler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author shenlx
 * @description
 * @date 2025/3/3 12:24
 */
@Configuration
public class SocketIOConfig implements InitializingBean {

    @Resource
    private SocketIOHandler socketIOHandler;

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


    @Override
    public void afterPropertiesSet() throws Exception {
        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setReuseAddress(true);
        socketConfig.setTcpNoDelay(true);
        socketConfig.setSoLinger(0);

        com.corundumstudio.socketio.Configuration configuration = new com.corundumstudio.socketio.Configuration();
        configuration.setSocketConfig(socketConfig);
        // host在本地测试可以设置为localhost或者本机IP，在Linux服务器跑可换成服务器IP
        configuration.setHostname(host);
        configuration.setPort(port);
        // socket连接数大小（如只监听一个端口boss线程组为1即可）
        configuration.setBossThreads(bossCount);
        configuration.setWorkerThreads(workCount);
        configuration.setAllowCustomRequests(allowCustomRequests);
        // 协议升级超时时间（毫秒），默认10秒。HTTP握手升级为ws协议超时时间
        configuration.setUpgradeTimeout(upgradeTimeout);
        // Ping消息超时时间（毫秒），默认60秒，这个时间间隔内没有接收到心跳消息就会发送超时事件
        configuration.setPingTimeout(pingTimeout);
        // Ping消息间隔（毫秒），默认25秒。客户端向服务器发送一条心跳消息间隔
        configuration.setPingInterval(pingInterval);

        SocketIOServer socketIOServer = new SocketIOServer(configuration);
        //添加事件监听器
        socketIOServer.addListeners(socketIOHandler);
        //启动SocketIOServer
        socketIOServer.start();
        System.out.println("SocketIO启动完毕");
    }
}