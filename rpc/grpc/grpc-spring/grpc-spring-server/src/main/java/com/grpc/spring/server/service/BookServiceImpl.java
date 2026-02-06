package com.grpc.spring.server.service;

import com.google.protobuf.StringValue;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import com.grpc.spring.proto.Book;
import com.grpc.spring.proto.BookServiceGrpc;

import java.util.*;

/**
 * @author shenlx
 * @description
 * @date 2026/2/10 10:26
 */
@Slf4j
public class BookServiceImpl extends BookServiceGrpc.BookServiceImplBase{


    private final Map<String, Book> bookMap = new HashMap<>();

    public BookServiceImpl() {
        com.grpc.spring.proto.Book b1 = com.grpc.spring.proto.Book.newBuilder().setId("1").setName("三国演义").setAuthor("罗贯中").setPrice(30).addTags("明清小说").addTags("通俗小说").build();
        com.grpc.spring.proto.Book b2 = com.grpc.spring.proto.Book.newBuilder().setId("2").setName("西游记").setAuthor("吴承恩").setPrice(40).addTags("志怪小说").addTags("通俗小说").build();
        com.grpc.spring.proto.Book b3 = com.grpc.spring.proto.Book.newBuilder().setId("3").setName("水浒传").setAuthor("施耐庵").setPrice(50).addTags("明清小说").addTags("通俗小说").build();
        bookMap.put("1", b1);
        bookMap.put("2", b2);
        bookMap.put("3", b3);
    }

    @Override
    public void addBook(com.grpc.spring.proto.Book request, StreamObserver<StringValue> responseObserver) {
        bookMap.put(request.getId(), request);
        responseObserver.onNext(StringValue.newBuilder().setValue(request.getId()).build());
        responseObserver.onCompleted();
        log.info("添加数据结构:{}", bookMap);
    }

    @Override
    public void getBook(StringValue request, StreamObserver<Book> responseObserver) {
        String id = request.getValue();
        com.grpc.spring.proto.Book book = bookMap.get(id);
        if (book != null) {
            responseObserver.onNext(book);
            log.info("添加数据结构:{}",book);
        }
        responseObserver.onCompleted();
    }

    @Override
    public void searchBooks(StringValue request, StreamObserver<Book> responseObserver) {
        Set<String> keySet = bookMap.keySet();
        String tags = request.getValue();
        for (String key : keySet) {
            com.grpc.spring.proto.Book book = bookMap.get(key);
            int tagsCount = book.getTagsCount();
            for (int i = 0; i < tagsCount; i++) {
                String t = book.getTags(i);
                if (t.equals(tags)) {
                    responseObserver.onNext(book);
                    break;
                }
            }
        }
        responseObserver.onCompleted();
    }


    @Override
    public StreamObserver<Book> updateBooks(StreamObserver<StringValue> responseObserver) {
        StringBuilder sb = new StringBuilder("update bookId is:");

        return new StreamObserver<Book>() {
            @Override
            public void onNext(Book book) {
                //每当服务端返回一次数据的时候，客户端回调的 onNext 方法就会被触发一次
                bookMap.put(book.getId(), book);
                sb.append(book.getId()).append(",");
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                log.info("数据处理:{}", bookMap);
                responseObserver.onNext(StringValue.newBuilder().setValue(sb.toString()).build());
                //当服务端之行了 responseObserver.onCompleted(); 之后，客户端的 onCompleted 方法也会被触发。
                responseObserver.onCompleted();
            }
        };
    }

    private final List<Book> books=new ArrayList<>();

    @Override
    public StreamObserver<StringValue> processBooks(StreamObserver<com.grpc.spring.proto.BookSet> responseObserver){
        return new StreamObserver<StringValue>() {
            @Override
            public void onNext(StringValue stringValue) {
                com.grpc.spring.proto.Book b = com.grpc.spring.proto.Book.newBuilder().setId(stringValue.getValue()).build();
                books.add(b);
                if(books.size()==3){
                    log.info("books-----:{}",books);
                    com.grpc.spring.proto.BookSet bookSet = com.grpc.spring.proto.BookSet.newBuilder().addAllBookList(books).build();
                    responseObserver.onNext(bookSet);
                    books.clear();
                }

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                com.grpc.spring.proto.BookSet bookSet= com.grpc.spring.proto.BookSet.newBuilder().addAllBookList(books).build();
                responseObserver.onNext(bookSet);
                log.info("BookSet:{}",bookSet);
                books.clear();
                responseObserver.onCompleted();
            }
        };

    }
}
