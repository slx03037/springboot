package com.executor.threadpool.channel.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

/**
 * @author shenlx
 * @description
 * @date 2024/2/6 20:48
 */
public class UDPEchoServerSelector {
    /**wait timeout (milliseconds)*/
    private static final int TIMEOUT=3000;
    /**Maximum size of echo datagram*/
    private static final int ECHOMAX=255;

    public static void main(String[]args) throws IOException {
        int servPort=5000;

        Selector selector = Selector.open();

        DatagramChannel channel = DatagramChannel.open();

        channel.configureBlocking(false);
        channel.socket().bind(new InetSocketAddress(servPort));
        channel.register(selector,SelectionKey.OP_READ,new ClientRecord());

        /**run forever,receiving and echoing datagrams*/
        while(true){
            /**wait for task or until timeout expires*/
            if(selector.select(TIMEOUT) == 0){
                System.out.println(".");
                continue;
            }

            //Get iterator on set of keys with I/O to process
            Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
            while (keyIter.hasNext()){
                //key is bit mask
                SelectionKey key = keyIter.next();

                //client socket channel has pending data
                if(key.isReadable()){
                    handlerRead(key);
                }

                //client socket channel is available for writing and key is valid（i.e.,channel not close）
                if(key.isValid() && key.isWritable()){
                    handlewrite(key);
                }

                keyIter.remove();
            }

        }
    }

    public static void handlerRead(SelectionKey key) throws IOException {
        DatagramChannel channel=(DatagramChannel) key.channel();
        ClientRecord clntRec = (ClientRecord) key.attachment();

        //Prepare buffer for receiving
        clntRec.buffer.clear();
        clntRec.socketAddress = channel.receive(clntRec.buffer);
        /**did we receive something*/
        if(clntRec.socketAddress != null){
            //Register write with the selector
            key.interestOps(SelectionKey.OP_WRITE);
        }
    }

    public static void handlewrite(SelectionKey key) throws IOException {
        DatagramChannel channel = (DatagramChannel) key.channel();
        ClientRecord clntREc = (ClientRecord) key.attachment();
        //Prepare buffer for sending
       clntREc.buffer.flip();
        int bytesSend = channel.send(clntREc.buffer, clntREc.socketAddress);

        /**Buffer completely written*/
        if(bytesSend != 0){
            //no longer interested in writes
            key.interestOps(SelectionKey.OP_READ);
        }
    }


    static class ClientRecord {
        public SocketAddress socketAddress;
        public ByteBuffer buffer = ByteBuffer.allocate(ECHOMAX);
    }

}
