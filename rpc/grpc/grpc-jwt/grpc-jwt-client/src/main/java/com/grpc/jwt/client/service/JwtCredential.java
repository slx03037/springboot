package com.grpc.jwt.client.service;


import com.grpc.jwt.common.AuthConstant;
import io.grpc.CallCredentials;
import io.grpc.Metadata;
import io.grpc.Status;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executor;

/**
 * @author shenlx
 * @description
 * @date 2025/4/1 22:22
 */
@Slf4j
public class JwtCredential extends CallCredentials {
    private final String subject;

    public JwtCredential(String subject) {
        this.subject = subject;
    }

    @Override
    public void applyRequestMetadata(RequestInfo requestInfo, Executor executor, MetadataApplier metadataApplier) {
        executor.execute(()->{
            try{
                Metadata metadata = new Metadata();
                metadata.put(Metadata.Key.of(AuthConstant.AUTH_HEADER,Metadata.ASCII_STRING_MARSHALLER)
                        ,String.format("%s %s",AuthConstant.AUTH_TOKEN_TYPE,subject));

                metadataApplier.apply(metadata);
            }catch (Exception e){
                log.info("处理请求报错:{}",e.getMessage());
                metadataApplier.fail(Status.UNAUTHENTICATED.withCause(e));
            }
        });
    }

    @Override
    public void thisUsesUnstableApi() {
        log.info("-------thisUsesUnstableApi");
    }
}
