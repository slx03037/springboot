package com.executor.threadpool.channel.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author shenlx
 * @description
 * @date 2026/3/6 16:10
 */
public class TCPEchoClientNonblocking {
    /**
     * SocketChannel clntchan=SocketChannel.open();
     * ServerSocketChannel servChan=ServerSocketChannel.open();
     *
     * TCP可以使用SocketChannel和ServerSocketChannel(FileChannel)，信道(channel)和套接字(socket)之间的不同点，可能使信道通常要调用静态工厂方法来获取实例
     * Channel使用的不是流，而是缓冲区来发生或读取数据，Buffer类或其任何子类的实例都可以看作使一个定长的java的基本数据类型元素序列。
     * 与流不同，缓冲区有固定的，有限的容量，并由内部(但可以被访问)状态记录了有多少数据放入或取出，就像是有限容量的队列一样。Buffer是一个抽象类，之恶能通过创建它的子类来获得Buffer实例
     * 而每个子类都设计为用来容纳一种java基本数据类型(boolean除外)。因此，这些实例分别为FloatBuffer，IntBuffer或ByteBuffer等(ByteBuffer是这些实例中最灵活的，并将在后面很多例子中用到)
     * 在channel中使用Buffer实例通常不是使用构造函数创建的，而是通过调用ByteBuffer.allocate()方法创建指定容量的Buffer实例
     * 或通过包装一个已有的数组来创建:ByteBuffer.wrap();
     *
     * NIO的强大功能部分来自于channel的非阻塞特性(套接字的某些操作可能会无限期地阻塞)
     * 例如，对accept()方法地调用可能会因为等待一个客户端连接而阻塞；对read()方法地调用可能会因为没有数据可读而阻塞，直到连接地另一端传来新地数据
     * 总的来说，创建/接收连接或读写数据等I/O调用，都可能无限期地阻塞等待，直到底层地网络实现发生了什么，慢速地，有损耗地网络，或仅仅是简单地网络故障
     * 都可能导致任何时间地延迟。
     * 在调用一个方法之前无法知道其是否会阻塞。NIO地channel抽象地一个重要特征就是可以通过配置它地阻塞行为，以实现非阻塞式地信道
     * clntchan.configureBlocking(false);
     * 在非阻塞式信道上调用一个方法总是会立即返回。这种调用地返回值指示了所请求地操作完成地程度。例如，在一个阻塞式ServerSocketChannel上调用accept()方法，
     * 如果有连接请求在等到，则返回客户端SocketChannel，否则返回null
     *
     */
    public static void main(String[]args) throws IOException {
        //获取并转换参数
        String server = "127.0.0.1";
        int serPort=8091;
        String msg="hello word";
        byte[] bytes = msg.getBytes();

        //创建非阻塞式SocketChannel
        //create channel and set to nonblocking
        SocketChannel clntchan=SocketChannel.open();
        //ServerSocketChannel servChan=ServerSocketChannel.open();

        clntchan.configureBlocking(false);
        //Initiate connection to server and repeatedly poll until complete
        if(!clntchan.connect(new InetSocketAddress(server,serPort))){
            while(!clntchan.finishConnect()){
                System.out.println(".");
            }
        }
        //创建读写缓冲区
        ByteBuffer writeBuf=ByteBuffer.wrap(bytes);
        ByteBuffer readBuf = ByteBuffer.allocate(bytes.length);

        //Total bytes received so far
        int totalBytesRcvd=0;

        //Bytes received in last read
        int bytesRcvd;

        while(totalBytesRcvd<bytes.length){
            if(writeBuf.hasRemaining()){
                clntchan.write(writeBuf);
            }

            if((bytesRcvd=clntchan.read(readBuf)) == -1){
                throw new SocketException("connection closed prematurely");
            }
            totalBytesRcvd +=bytesRcvd;
            System.out.println(".");
        }
        //convert to String per default charset
        System.out.println("Received:"+new String(readBuf.array(),0,totalBytesRcvd));
        clntchan.close();
    }
}
