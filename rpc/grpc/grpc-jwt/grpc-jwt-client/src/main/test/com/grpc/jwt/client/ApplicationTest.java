package com.grpc.jwt.client;

import com.google.protobuf.StringValue;
import com.grpc.jwt.client.service.JwtCredential;
import com.xinwen.grpc.jwt.api.HelloServiceGrpc;
import com.xinwen.grpc.jwt.api.LoginBody;
import com.xinwen.grpc.jwt.api.LoginResponse;
import com.xinwen.grpc.jwt.api.LoginServiceGrpc;
import io.grpc.Deadline;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author shenlx
 * @description
 * @date 2026/2/10 11:13
 */
@SpringBootTest
@Slf4j
public class ApplicationTest {

    @Test
    public void test() throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 15501)
                .usePlaintext()
                .build();

        LoginServiceGrpc.LoginServiceStub stub = LoginServiceGrpc
                .newStub(channel)
                .withDeadline(Deadline.after(3, TimeUnit.SECONDS));//设置超时时间
        login(stub);
    }

    private static void login(LoginServiceGrpc.LoginServiceStub stub) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        stub.login(LoginBody.newBuilder().setUsername("xiaoming").setPassword("123").build()
                , new StreamObserver<LoginResponse>() {

                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        log.info("login response token is:{}", loginResponse.getToken());
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        //超时时间爆粗
                        log.error("报错原因:{}",throwable.getMessage());
                    }

                    @Override
                    public void onCompleted() {
                        log.info("加载完毕");
                        countDownLatch.countDown();
                    }
                });
        //释放资源
        countDownLatch.await();
    }

    //---eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ4aWFvbWluZyJ9.y8QgJ1IQaNa-bfzfhYTcqsYPDzaZxp2CwmQlyKlKfAP7FLCrqsgj7clnmmrmPbbF

    @Test
    public void testSayHello() throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 15501)
                .usePlaintext()
                .build();

        HelloServiceGrpc.HelloServiceStub stub = HelloServiceGrpc.newStub(channel);
        sayHello(stub);
    }

    public static void sayHello(HelloServiceGrpc.HelloServiceStub stub) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        stub.withCallCredentials(new JwtCredential("eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ4aWFvbWluZyJ9.y8QgJ1IQaNa-bfzfhYTcqsYPDzaZxp2CwmQlyKlKfAP7FLCrqsgj7clnmmrmPbbF"))
                .sayHello(StringValue.newBuilder().setValue("hello world").build(), new StreamObserver<StringValue>() {
                    @Override
                    public void onNext(StringValue stringValue) {
                        log.info("结束服务端返回数据:{};", stringValue.getValue());
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("throwable.getMessage() = " + throwable.getMessage());
                    }

                    @Override
                    public void onCompleted() {
                        log.info("加载完毕");
                        countDownLatch.countDown();
                    }
                });
        countDownLatch.await();

    }
}
