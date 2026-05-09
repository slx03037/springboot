package com.executor.threadpool.channel.tcp;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author shenlx
 * @description
 * @date 2026/3/6 16:09
 */
public class EchoSelectorProtocol implements TCPProtocol{

    private final int bufSize;

    public EchoSelectorProtocol(int bufSize){
        this.bufSize=bufSize;
    }

    @Override
    public void handleAccept(SelectionKey key) throws IOException {
        SocketChannel clntChan = ((ServerSocketChannel) key.channel()).accept();
        //must be nonblocking to register
        clntChan.configureBlocking(false);
        //register the selector with new channel for read and attach byte buffer
        clntChan.register(key.selector(),SelectionKey.OP_READ, ByteBuffer.allocate(bufSize));
    }

    @Override
    public void handlerRead(SelectionKey key) throws IOException {
        SocketChannel clntChan = (SocketChannel) key.channel();
        ByteBuffer buf = (ByteBuffer) key.attachment();
        int bytesRead = clntChan.read(buf);
        //did the other end close
        if(bytesRead == -1){
            clntChan.close();
        }else if(bytesRead>0){
            //Indicate via key that reading/writing are both of interest now
            key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        }
    }

    @Override
    public void handlerWrite(SelectionKey key) throws IOException {
        /**
         * channel is available for writing .and key is valid (i.e., client channel not closed)
         */
        //Retrieve data read earlier
        ByteBuffer buf = (ByteBuffer) key.attachment();
        //prepare buffer for writing
        buf.flip();

        SocketChannel clntChan = (SocketChannel) key.channel();
        clntChan.write(buf);
        //Buffer completely wirtten ？ Nothing left,so no longer interested in wirtes
        if(!buf.hasRemaining()){
            key.interestOps(SelectionKey.OP_READ);
        }
        //make room for more data to be read in
        buf.compact();
    }
}
