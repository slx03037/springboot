package com.grpc.jwt.server.service;

import com.google.protobuf.StringValue;
import com.grpc.jwt.common.AuthConstant;
import com.xinwen.grpc.jwt.api.HelloServiceGrpc;
import org.springframework.stereotype.Component;

/**
 * @author shenlx
 * @description
 * @date 2025/3/31 16:59
 */
@Component
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
    @Override
    public void sayHello(StringValue request,
                         io.grpc.stub.StreamObserver<StringValue> responseObserver) {
        String clientId = AuthConstant.AUTH_CLIENT_ID.get();
        responseObserver.onNext(StringValue.newBuilder().setValue(clientId + " say hello:" + request.getValue()).build());
        responseObserver.onCompleted();
    }
}
