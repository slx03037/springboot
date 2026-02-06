package com.grpc.filter.client;


import com.google.protobuf.StringValue;
import com.xinwen.grpc.filter.common.Student;
import com.xinwen.grpc.filter.common.StudentId;
import com.xinwen.grpc.filter.common.StudentServiceGrpc;
import com.xinwen.grpc.filter.common.StudentSet;
import io.grpc.*;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

/**
 * @author shenlx
 * @description
 * @date 2025/3/31 12:03
 */
@SpringBootTest
@Slf4j
public class ApplicationTest {

    @Test
    public void testAdd() {
        //构建服务通道
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 15501)
                .usePlaintext()
                .build();

        //这个是阻塞调用的gRPC客户端类型，实际使用中跟HTTP接口请求->响应
        StudentServiceGrpc.StudentServiceBlockingStub stub = StudentServiceGrpc.newBlockingStub(channel);

        Student student = Student.newBuilder()
                .setId("5")
                .setName("xiao hu")
                .setClass_("2")
                .setDescription("is female")
                .build();
        StudentId studentId = stub.addStudent(student);
        log.info("添加学生信息:{}", studentId);
    }

    @Test
    public void getStudent() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 15501)
                .usePlaintext()
                .build();

        StudentServiceGrpc.StudentServiceBlockingStub stub = StudentServiceGrpc.newBlockingStub(channel);
        Student student = Student.newBuilder()
                .setId("1").build();
        Student student1 = stub.getStudent(student);
        log.info("获取学生信息:{}", student1);
    }

    @Test
    public void testSearch() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 15501)
                .usePlaintext()
                .build();
        StudentServiceGrpc.StudentServiceStub stub = StudentServiceGrpc.newStub(channel);

        stub.searchBooks(StringValue.newBuilder().setValue("2").build(), new StreamObserver<Student>() {
            @Override
            public void onNext(Student student) {
                log.info("结束传递的学生信息:{}", student);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                countDownLatch.countDown();
                log.info("程序运行结束");
            }
        });

        countDownLatch.await();
    }

    @Test
    public void testUpdates() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 15501)
                .usePlaintext()
                .build();
        StudentServiceGrpc.StudentServiceStub stub = StudentServiceGrpc.newStub(channel);
        StreamObserver<Student> streamObserver = stub.updateStudents(new StreamObserver<StringValue>() {
            @Override
            public void onNext(StringValue stringValue) {
                log.info("输出结果：{}", stringValue);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                log.info("更新完毕");
                countDownLatch.countDown();
            }
        });

        Student s4 = Student.newBuilder().setId("4").setName("xiao lan").setDescription("female").setClass_("2").setScore(80).build();

        streamObserver.onNext(s4);

        streamObserver.onCompleted();
        countDownLatch.await();
    }

    @Test
    public void testProcess() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 15501)
                .usePlaintext()
                .build();
        StudentServiceGrpc.StudentServiceStub stub = StudentServiceGrpc.newStub(channel);

        StreamObserver<StringValue> observer = stub.processStudents(new StreamObserver<StudentSet>() {
            @Override
            public void onNext(StudentSet studentSet) {
                log.info("结束的数据:{}", studentSet);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                log.info("程序运行结束");
                countDownLatch.countDown();
            }
        });
        observer.onNext(StringValue.newBuilder().setValue("2").build());
        observer.onCompleted();
        countDownLatch.await();

    }

    @Test
    public void testIntercept(){
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .intercept(new ClientInterceptor() {
                    @Override
                    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next) {
                        System.out.println("!!!!!!!!!!!!!!!!");
                        callOptions = callOptions.withAuthority("javaboy");
                        return next.newCall(method,callOptions);
                    }
                })
                .build();
        StudentServiceGrpc.StudentServiceStub stub = StudentServiceGrpc.newStub(channel);
    }
}