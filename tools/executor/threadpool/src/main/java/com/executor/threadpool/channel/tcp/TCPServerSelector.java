package com.executor.threadpool.channel.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.charset.*;
import java.util.Iterator;

/**
 * @author shenlx
 * @description
 * @date 2026/3/6 16:10
 */
public class TCPServerSelector {
    //buffer size(bytes)
    private static final int BUFSIZE=256;
    //wait timeout(milliseconds)
    private static final int TIMEOUT=3000;

    public static void main(String[]args) throws IOException {
        Selector selector=Selector.open();
        ServerSocketChannel open = ServerSocketChannel.open();
        open.close();
        open.isOpen();

        ServerSocketChannel listenChannel = ServerSocketChannel.open();

        InetSocketAddress inetSocketAddress = new InetSocketAddress(8091);

        listenChannel.socket().bind(inetSocketAddress);

        listenChannel.configureBlocking(false);

        //Register selector with channel.The returned key is ignored
        listenChannel.register(selector, SelectionKey.OP_ACCEPT);



        //create a handler that will implement the protocol
        EchoSelectorProtocol echoSelectorProtocol = new EchoSelectorProtocol(BUFSIZE);

        while(true){
            //run forever ,processing available I/O operations wait for some channel to be ready(or timeout)
            if(selector.select(TIMEOUT) == 0){
                //returns of ready chans
                System.out.println(".");
                continue;
            }

            //GET iterator on set keys with I/O to process
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();

            while(keyIterator.hasNext()){
                //key is not mask
                SelectionKey key = keyIterator.next();
                //server socket channel has pending connection requests?
                if(key.isAcceptable()){
                    echoSelectorProtocol.handleAccept(key);
                }
                //client socket channel has pending data?
                if(key.isReadable()){
                    echoSelectorProtocol.handlerRead(key);
                }
                //client socket channel is available for writing and key is valid(i.e.,channel not closed)?
                if(key.isValid() && key.isWritable()){
                    echoSelectorProtocol.handlerWrite(key);
                }
                //remove from set of selected keys
                keyIterator.remove();

                DatagramChannel open1 = DatagramChannel.open();
                open1.close();
            }
        }



    }

    public static ByteBuffer encoder() throws CharacterCodingException {
        Charset charset = StandardCharsets.US_ASCII;
        CharsetEncoder charsetEncoder = charset.newEncoder();
        ByteBuffer helloWord = charsetEncoder.encode(CharBuffer.wrap("hello word"));
        return helloWord;
    }

    public CharBuffer decode(ByteBuffer buffer) throws CharacterCodingException {
        Charset charset = StandardCharsets.US_ASCII;
        CharsetDecoder charsetDecoder = charset.newDecoder();
        CharBuffer decode = charsetDecoder.decode(buffer);
        return decode;
    }
}
