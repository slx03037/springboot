package com.grpc.spring.client;

import com.google.protobuf.StringValue;
import com.grpc.spring.proto.Book;
import com.grpc.spring.proto.BookServiceGrpc;
import com.grpc.spring.proto.BookSet;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

/**
 * @author shenlx
 * @description
 * @date 2026/2/10 10:33
 */
@SpringBootTest
@Slf4j
public class ApplicationTest {

    @Test
    public void test() throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        //这是个纯异步调用客户端
        BookServiceGrpc.BookServiceStub stub = BookServiceGrpc.newStub(channel);
        addBook(stub);
    }

    private static void addBook(BookServiceGrpc.BookServiceStub stub) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        stub.addBook(Book.newBuilder().setPrice(99).setId("100").setName("GRPC").setAuthor("seven").build(), new StreamObserver<StringValue>() {
            @Override
            public void onNext(StringValue stringValue) {
                System.out.println("stringValue.getValue() = " + stringValue.getValue());
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                countDownLatch.countDown();
                System.out.println("添加完毕");
            }
        });
        countDownLatch.await();
    }


    @Test
    public void test1() throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        BookServiceGrpc.BookServiceStub stub = BookServiceGrpc.newStub(channel);
        getBook(stub);
    }

    private static void getBook(BookServiceGrpc.BookServiceStub stub) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        stub.getBook(StringValue.newBuilder().setValue("1").build(), new StreamObserver<Book>() {
            @Override
            public void onNext(Book book) {
                System.out.println("book = " + book);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("未查询到");
            }

            @Override
            public void onCompleted() {
                countDownLatch.countDown();
                System.out.println("查询完毕");
            }
        });
        countDownLatch.await();
    }


    @Test
    public void test2() throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        BookServiceGrpc.BookServiceStub stub = BookServiceGrpc.newStub(channel);
        searchBook(stub);
    }

    private static void searchBook(BookServiceGrpc.BookServiceStub stub) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        stub.searchBooks(StringValue.newBuilder().setValue("明清小说").build(), new StreamObserver<Book>() {
            @Override
            public void onNext(Book book) {
                System.out.println(book);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                countDownLatch.countDown();
                System.out.println("查询完毕！");
            }
        });
        countDownLatch.await();
    }


    @Test
    public void test3() throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        BookServiceGrpc.BookServiceStub stub = BookServiceGrpc.newStub(channel);
        updateBook(stub);

    }

    private static void updateBook(BookServiceGrpc.BookServiceStub stub) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        StreamObserver<Book> request = stub.updateBooks(new StreamObserver<StringValue>() {
            @Override
            public void onNext(StringValue stringValue) {
                log.info("StringValue.getValue()=" + stringValue.getValue());
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

        request.onNext(Book.newBuilder().setId("1").setName("a").setAuthor("aa").build());
        request.onNext(Book.newBuilder().setId("2").setName("b").setAuthor("bb").build());
        request.onCompleted();
        countDownLatch.await();
    }

    @Test
    public void test4() throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        BookServiceGrpc.BookServiceStub stub = BookServiceGrpc.newStub(channel);
        processBook(stub);
    }

    private static void processBook(BookServiceGrpc.BookServiceStub stub) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        StreamObserver<StringValue> request = stub.processBooks(new StreamObserver<BookSet>() {
            @Override
            public void onNext(BookSet bookSet) {
                log.info("bookSet:{};", bookSet);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                log.info("处理完毕");
                countDownLatch.countDown();
            }
        });
        request.onNext(StringValue.newBuilder().setValue("a").build());
        request.onNext(StringValue.newBuilder().setValue("b").build());
        request.onNext(StringValue.newBuilder().setValue("c").build());
        request.onNext(StringValue.newBuilder().setValue("d").build());
        request.onCompleted();
        countDownLatch.await();
    }
}
