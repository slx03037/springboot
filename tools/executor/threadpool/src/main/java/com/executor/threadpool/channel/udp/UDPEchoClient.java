package com.executor.threadpool.channel.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author shenlx
 * @description
 * @date 2024/2/7 9:14
 */
public class UDPEchoClient {
    public static void main(String[]args) throws IOException {
        DatagramChannel channel=DatagramChannel.open();

        channel.socket().bind(new InetSocketAddress(5000));

        ByteBuffer buffer=ByteBuffer.allocateDirect(20);

        SocketAddress receive = channel.receive(buffer);

        buffer.flip();

        StandardCharsets.UTF_16.newEncoder().encode(buffer.asCharBuffer()).toString();

    }

}
