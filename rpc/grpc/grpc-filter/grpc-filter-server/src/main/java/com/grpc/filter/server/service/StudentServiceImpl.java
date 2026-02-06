package com.grpc.filter.server.service;

import com.google.protobuf.StringValue;
import com.xinwen.grpc.filter.common.Student;
import com.xinwen.grpc.filter.common.StudentId;
import com.xinwen.grpc.filter.common.StudentServiceGrpc;
import com.xinwen.grpc.filter.common.StudentSet;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author shenlx
 * @description
 * @date 2026/2/10 10:46
 */
@Slf4j
public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {
    private final Map<String, Student> studentMap = new HashMap<>();

    public StudentServiceImpl() {
        Student s1 = Student.newBuilder().setId("1").setName("xiao ming").setDescription("female").setClass_("2").setScore(20).build();
        Student s2 = Student.newBuilder().setId("2").setName("xiao hao").setDescription("male").setClass_("2").setScore(30).build();
        Student s3 = Student.newBuilder().setId("3").setName("xiao xin").setDescription("male").setClass_("2").setScore(40).build();
        Student s4 = Student.newBuilder().setId("4").setName("xiao lan").setDescription("female").setClass_("2").setScore(50).build();
        studentMap.put(s1.getId(), s1);
        studentMap.put(s2.getId(), s2);
        studentMap.put(s3.getId(), s3);
        studentMap.put(s3.getId(), s4);

    }

    @Override
    public void addStudent(com.xinwen.grpc.filter.common.Student request,
                           io.grpc.stub.StreamObserver<com.xinwen.grpc.filter.common.StudentId> responseObserver) {
        studentMap.put(request.getId(), request);
        log.info("插入内容:{}", request);
        log.info("学生集合:{}", studentMap);

        // io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddStudentMethod(), responseObserver);
        responseObserver.onNext(StudentId.newBuilder().setValue(request.getId()).build());
        responseObserver.onCompleted();
    }

    @Override
    public void getStudent(com.xinwen.grpc.filter.common.Student request,
                           io.grpc.stub.StreamObserver<com.xinwen.grpc.filter.common.Student> responseObserver) {
        Student student = studentMap.get(request.getId());
        log.info("学生信息:{}", student);
        responseObserver.onNext(student);
//        responseObserver.onNext(Student.newBuilder()
//                .setId(student.getId())
//                .setName(student.getName())
//                .setClass_(student.getClass_())
//                .setScore(student.getScore())
//                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void searchBooks(com.google.protobuf.StringValue request,
                            io.grpc.stub.StreamObserver<com.xinwen.grpc.filter.common.Student> responseObserver) {
        Set<String> stuKeys = studentMap.keySet();
        String value = request.getValue();
        log.info("传递的值为:{}",value);
        for(String key:stuKeys){
            if(!key.equals(value)){
                Student student = studentMap.get(key);
                log.info("传递的学生信息:{}",student);
                responseObserver.onNext(student);
            }
        }
        responseObserver.onCompleted();
    }

    @Override
    public io.grpc.stub.StreamObserver<com.xinwen.grpc.filter.common.Student> updateStudents(
            io.grpc.stub.StreamObserver<com.google.protobuf.StringValue> responseObserver) {
        StringBuilder sb=new StringBuilder();
        return new StreamObserver<Student>() {
            @Override
            public void onNext(Student student) {
                log.info("接收的学生信息并修改:{}",student);
                sb.append(student.getId()).append(",");
                studentMap.put(student.getId(),student);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                responseObserver.onNext(StringValue.newBuilder().setValue(sb.toString()).build());
                //当服务端之行了 responseObserver.onCompleted(); 之后，客户端的 onCompleted 方法也会被触发。
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public io.grpc.stub.StreamObserver<com.google.protobuf.StringValue> processStudents(
            io.grpc.stub.StreamObserver<com.xinwen.grpc.filter.common.StudentSet> responseObserver) {
        List<Student> students=new ArrayList<>();
        return new StreamObserver<StringValue>() {
            @Override
            public void onNext(StringValue stringValue) {
                log.info("查询参数:{}",stringValue.getValue());
                Student student = studentMap.get(stringValue.getValue());
                log.info("查询结果:{}",student);
                students.add(student);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                StudentSet studentSet = StudentSet.newBuilder().setId("1").addAllStudentList(students).build();
                responseObserver.onNext(studentSet);
                responseObserver.onCompleted();
            }
        };
    }
}