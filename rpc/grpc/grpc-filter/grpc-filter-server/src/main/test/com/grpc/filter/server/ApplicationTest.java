package com.grpc.filter.server;

import com.grpc.filter.server.lister.StudentServiceRequestListener;
import com.grpc.filter.server.lister.StudentServiceResponseListener;
import com.grpc.filter.server.service.StudentServiceImpl;
import io.grpc.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Set;

import static io.grpc.Metadata.ASCII_STRING_MARSHALLER;

/**
 * @author shenlx
 * @description
 * @date 2025/3/31 12:04
 */
@SpringBootTest
@Slf4j
public class ApplicationTest {
    Server server;

    @Test
    public void test() throws IOException, InterruptedException {
        //构建rpc服务端
        server = ServerBuilder.forPort(15501)//端口
                .addService(new StudentServiceImpl())//引用rpc第三方的服务
                .build();

        //启动
        server.start();
        Runtime.getRuntime().addShutdownHook(new Thread(this::stop));
        //停机
        this.blockUntilShutdown();
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


    @Test
    public void testFilter() throws IOException, InterruptedException {
        ServerBuilder.forPort(15501)
                .addService(ServerInterceptors.intercept(new StudentServiceImpl(), new ServerInterceptor() {
                    @Override
                    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler) {
                        String fullMethodName = serverCall.getMethodDescriptor().getFullMethodName();
                        log.info(fullMethodName + ":pre");
                        Set<String> keys = metadata.keys();

                        for (String key : keys) {
                            log.info("-----------{}>>>>{}", key, metadata.get(Metadata.Key.of(key, ASCII_STRING_MARSHALLER)));
                        }
                        return new StudentServiceRequestListener<>(serverCallHandler.startCall(new StudentServiceResponseListener(serverCall), metadata));
                    }
                }))
                .build()
                .start();
        Runtime.getRuntime().addShutdownHook(new Thread(this::stop));
        //停机
        this.blockUntilShutdown();
    }
}
