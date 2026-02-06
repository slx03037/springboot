package com.grpc.filter.server.lister;

import io.grpc.ForwardingServerCall;
import io.grpc.MethodDescriptor;
import io.grpc.ServerCall;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shenlx
 * @description
 * @date 2025/3/31 15:09
 */
@Slf4j
public class StudentServiceResponseListener<ReqT,RespT> extends ForwardingServerCall.SimpleForwardingServerCall<ReqT,RespT> {
    public StudentServiceResponseListener(ServerCall delegate) {
        super(delegate);
    }

    @Override
    protected  ServerCall<ReqT,RespT> delegate(){
        return super.delegate();
    }

    @Override
    public MethodDescriptor<ReqT,RespT> getMethodDescriptor(){
        return super.getMethodDescriptor();
    }


    @Override
    public void sendMessage(RespT message) {
        log.info("这是服务推送给客户端的消息:{}",message);
        super.sendMessage(message);
    }
}
