package com.grpc.jwt.server.service;


import com.grpc.jwt.server.interceptor.AuthInterceptor;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;

import java.io.IOException;

/**
 * @author shenlx
 * @description
 * @date 2025/4/1 17:36
 */
public class LoginServer {
    Server server;

    public static void main(String[] args) throws IOException, InterruptedException {
        LoginServer server = new LoginServer();
        server.start();
        server.blockUntilShutdown();
    }

    public void start() throws IOException {
        int port = 50051;
        server = ServerBuilder.forPort(port)
                .addService(new LoginServiceImpl())
                .addService(ServerInterceptors.intercept(new HelloServiceImpl(), new AuthInterceptor()))
                .build()
                .start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LoginServer.this.stop();
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}
