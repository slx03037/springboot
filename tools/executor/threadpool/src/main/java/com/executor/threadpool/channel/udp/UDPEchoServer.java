package com.executor.threadpool.channel.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author shenlx
 * @description
 * @date 2024/2/7 9:00
 */
public class UDPEchoServer {

    public static void main(String[]args) throws IOException {
        DatagramChannel channel=DatagramChannel.open();

        ByteBuffer buffer = ByteBuffer.wrap("hello".getBytes(StandardCharsets.UTF_16));

        channel.send(buffer,new InetSocketAddress("localhost",5000));
    }
}
