package com.grpc.spring.server;

import com.grpc.spring.server.service.BookServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * @author shenlx
 * @description
 * @date 2026/2/10 10:29
 */
@SpringBootTest
public class ApplicationTest {

    Server server;

    @Test
    public void test() throws IOException, InterruptedException {
        start();
        blockUntilShutdown();
    }

    public void start() throws IOException {
        int port=50051;
        server = ServerBuilder.forPort(port)
                .addService(new BookServiceImpl())
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
