package com.netty.basic.web.netty.bytebuffer;

import io.netty.buffer.AbstractReferenceCountedByteBuf;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.UnpooledHeapByteBuf;

import java.nio.ByteBuffer;

/**
 * @author shenlx
 * @description
 * @date 2024/7/4 9:54
 */
public class TestMain {
    public static void main(String[]args){
        ByteBuffer buffer=ByteBuffer.allocate(88);
        String value="netty 权威指南";
        buffer.put(value.getBytes());
        //buffer.flip();
        //ByteBuf
        byte[]array=new byte[buffer.remaining()];
        buffer.get(array);
        String str = new String(array);
        System.out.println(str);

    }
}
