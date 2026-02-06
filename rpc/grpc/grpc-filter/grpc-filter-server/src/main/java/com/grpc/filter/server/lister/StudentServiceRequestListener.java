package com.grpc.filter.server.lister;

import io.grpc.ForwardingServerCallListener;
import io.grpc.ServerCall;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shenlx
 * @description
 * @date 2025/3/31 15:06
 */
@Slf4j
public class StudentServiceRequestListener<R> extends ForwardingServerCallListener<R> {
    private final ServerCall.Listener<R> delegate;

    public StudentServiceRequestListener(ServerCall.Listener<R> delegate) {
        this.delegate = delegate;
    }

    @Override
    protected ServerCall.Listener<R> delegate() {
        return delegate;
    }

    @Override
    public void onMessage(R message){
        log.info("这是客户端发来的消息，可以在这里进行预处理:{}",message);
        delegate.onMessage(message);
    }
}
