package com.netty.basic.web.netty.agreement.messagecode.decode;

import com.netty.basic.web.netty.agreement.datastructure.MarshallingCodecFactory;
import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.Unmarshaller;

import java.io.IOException;

/**
 * @author shenlx
 * @description
 * @date 2024/4/1 9:35
 */
public class MarshallingDecoder {
    private final Unmarshaller unmarshaller;

    public MarshallingDecoder() throws IOException {
        unmarshaller = MarshallingCodecFactory.buildUnMarshalling();
    }

//    protected Object decode(ByteBuf in) throws Exception {
//        //1. 读取第一个4bytes，里面放置的是object对象的byte长度
//        int objectSize = in.readInt();
//        ByteBuf buf = in.slice(in.readerIndex(), objectSize);
//        //2 . 使用bytebuf的代理类
//        ByteInput input = new ChannelBufferByteInput(buf);
//        try {
//            //3. 开始解码
//            unmarshaller.start(input);
//            Object obj = unmarshaller.readObject();
//            unmarshaller.finish();
//            //4. 读完之后设置读取的位置
//            in.readerIndex(in.readerIndex() + objectSize);
//            return obj;
//        } finally {
//            unmarshaller.close();
//        }
//    }

    public Object decode(ByteBuf in) throws Exception {
        try {
            //1 首先读取4个长度(实际body内容长度)
            int bodySize = in.readInt();
            //2 获取实际body的缓冲内容
            int readIndex = in.readerIndex();
            ByteBuf buf = in.slice(readIndex, bodySize);
            //3 转换
            ChannelBufferByteInput input = new ChannelBufferByteInput(buf);
            //4 读取操作:
            this.unmarshaller.start(input);
            Object ret = this.unmarshaller.readObject();
            this.unmarshaller.finish();
            //5 读取完毕以后, 更新当前读取起始位置:
            //因为使用slice方法，原buf的位置还在readIndex上，故需要将位置重新设置一下
            in.readerIndex(in.readerIndex() + bodySize);

            return ret;

        } finally {
            this.unmarshaller.close();
        }
    }
}
