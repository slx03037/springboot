package com.grpc.basic.server;

import com.grpc.basic.server.service.ProductInfoImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * @author shenlx
 * @description
 * @date 2025/3/26 13:27
 */
@SpringBootTest
public class SpringbootTest {
    Server server;

    @Test
    public void test() throws IOException, InterruptedException {
        start();
        blockUntilShutdown();
    }

    public void start() throws IOException {
        int port=50051;
        server = ServerBuilder.forPort(port)
                .addService(new ProductInfoImpl())
                .build()
                .start();
        Runtime.getRuntime().addShutdownHook(new Thread(this::stop));
    }

    private void stop(){
        if(server !=null){
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if(server !=null){
            server.awaitTermination();
        }
    }
}
