package com.grpc.jwt.server.service;

import com.xinwen.grpc.jwt.api.LoginResponse;
import com.xinwen.grpc.jwt.api.LoginServiceGrpc;
import com.xinwen.grpc.jwt.common.AuthConstant;
import io.grpc.Status;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author shenlx
 * @description
 * @date 2025/3/31 16:50
 */
@Slf4j
@Component
public class LoginServiceImpl extends LoginServiceGrpc.LoginServiceImplBase{
    @Override
    public void login(com.xinwen.grpc.jwt.api.LoginBody request,
                      io.grpc.stub.StreamObserver<com.xinwen.grpc.jwt.api.LoginResponse> responseObserver) {
        String username = request.getUsername();
        String password = request.getPassword();

        if("xiaoming".equals(username) && "123".equals(password)){
            log.info("login success");

            //登录成功
            String token = Jwts.builder().setSubject(username)
                    .signWith(AuthConstant.JWT_KEY)
                    .compact();
            log.info("token:{}",token);
            responseObserver.onNext(LoginResponse.newBuilder().setToken(token).build());
            responseObserver.onCompleted();
        }else {
            log.error("login failed");
//            responseObserver.onNext(LoginResponse.newBuilder().setToken("login failed").build());
//            responseObserver.onCompleted();
            //服务端登录异常
            responseObserver.onError(Status.UNAUTHENTICATED.withDescription("login error").asException());
        }
    }
}
