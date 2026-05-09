package com.executor.threadpool.channel.tcp;

import java.io.IOException;
import java.nio.channels.SelectionKey;

/**
 * @author shenlx
 * @description
 * @date 2026/3/6 16:10
 */
public interface TCPProtocol {
    void handleAccept(SelectionKey key)throws IOException;

    void handlerRead(SelectionKey key)throws IOException;

    void handlerWrite(SelectionKey key)throws IOException;
}
