package com.netty.basic.web.exceptiondemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author shenlx
 * @description
 * @date 2024/8/12 9:51
 */
@Slf4j
public class ExitMain0 {


    public static void main1(String[]args) throws InterruptedException {
        //new ExitMain0().start();
        //new ExitMain0().start1();
        new ExitMain0().start2();
    }
    /**
     * 通过阻塞方式绑定监听断开，启动服务端之后，没有发生异常，程序退出
     */
    public void start() throws InterruptedException {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap b=new ServerBootstrap();
            b.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler((new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new LoggingHandler(LogLevel.INFO));
                            p.addLast(new StringEncoder());
                            p.addLast(new StringDecoder());
                        }
                    }));
            b.bind(18080).sync();//同步方式绑定服务断开
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }



    public void start1() throws InterruptedException {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap b=new ServerBootstrap();
            b.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler((new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new LoggingHandler(LogLevel.INFO));
                            p.addLast(new StringEncoder());
                            p.addLast(new StringDecoder());
                        }
                    }));

            //b.bind(18080).sync();//同步方式绑定服务断开
            ChannelFuture cf = b.bind(18080).sync();
            //监听Close Future ，还会发生服务器套接字直接关系，进程退出的问题
            cf.channel().closeFuture().addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                   log.info(future.channel().toString()+"链路关闭");
                }
            });
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    /**
     * Daemon线程
     * 所谓守护线程（Daemon）就是运行在程序后台的线程，通常守护线程是由JVM创建的，用于辅助用户线程或者JVM工作，比较典型的如GC线程。
     * 用户创建的线程也可以设置成Daemon线程(通常需要谨慎设置)，程序的主线程(main 线程)不是守护线程，Daemon线程在java里面的定义是，
     * 如果虚拟机中只有Daemon线程运行，则虚拟机退出
     * 1.虚拟机中可能同时有多个线程运行，只有当所有的非守护线程(通都是用户线程)都结束的时候，虚拟机的进程才会结束，不管当前的线程是不是main线程。
     * 2.main线程运行结束，如果此时运行的其他的线程全部是Daemon线程，JVM使这些线程停止，同时退出，但是如果此时正在运行的其他线程有非守护线程，
     * 那么必须等待所有的非守护线程结束，JVM才会退出
     */

//    public static void main(String[]args) throws InterruptedException {
//        long startTime=System.nanoTime();
//        Thread t=new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try{
//                    log.info("程序开始执行");
//                    TimeUnit.DAYS.sleep(Long.MAX_VALUE);
//                    log.info("程序结束执行");
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        },"Daemon-T");
//        t.setDaemon(true);
//        //t.setDaemon(false);//也可以不设置Daemon属性，默认为false
//        t.start();
//        TimeUnit.SECONDS.sleep(15);
//        log.info("系统退出，程序执行:{}秒",(System.nanoTime()-startTime)/1000/1000/100);
//    }


    /**
     * 在Netty中，通过bootstrap.bind(18080).sync().channel()方法绑定服务断开，并不是在调用方的线程
     * 最终的执行结果其实就是调用java NIO Socket的端口绑定操作javaChannel.socket().bind(localAddress, config.getBacklog())
     *端口绑定操作执行完成之后，main函数就不会阻塞，如果后续没有同步代码，main线程就会退出，main线程退出并不意味着JVM进程退出，只有所有非守护线程全部执行完成，进程才会退出，
     * 1.NioEventLoop是非守护线程
     * 2.NioEventLoop运行之后，不会主动退出
     * 3.只有调用shutdown系列方法，NioEventLoop才会退出
     *
     * 两个退出的案例的原因:
     * 1.调用b.bind(18080).sync()之后，尽管它会同步阻塞，等待端口绑定结果，但是端口绑定执行得非常快，完成后程序就继续向下执行
     * 2，程序在finally里面执行了 bossGroup.shutdownGracefully()和workGroup.shutdownGracefully(),它同时会关闭服务端得TCP
     * 连接接入线程池(bossGroup)和处理客户端网络I/O读写得工作线程池(workGroup),关闭之后，NioEventLoop线程退出，整个系统得非守护线程就
     * 全部执行完成，此时main函数主线程也早已执行完成，因此JVM就会退出，因为调用得是Netty得优雅退出接口(shutdownGracefully)
     * ，所以整个退出过程并没有发生异常。
     *
     * 出现案例2得问题，主要原因是使用者并没有掌握Netty得ChannelFuture得机制，Netty是一个异步非阻塞得通信框架，所有得I/O操作都是异步，
     * 但是为了方便使用，例如在有些场景下应用需要同步阻塞等待一些I/O操作得结果，结果提供了ChannelFuture，它主要提供一些两种能力
     * 1.通过注册监听器GenericFutureListener，也可以异步等待I/O执行结果
     * 2.通过sync或者await，主动阻塞当前调用的方法，等待操作结果，也就是通常说的异步转同步
     * 案例2中增加了服务端连接关闭的监听事件之后，不会阻塞main()线程的执行，端口绑定成功之后，main线程继续向下执行，由于在finally中增加了线程池
     * 关闭代码，NioEventLoop线程会主动退出，系统中没有正在运行的非守护线程，JVM进程退出
     */


    /**
     * 如何防止Netty服务端意味退出
     * 1.注释掉 bossGroup.shutdownGracefully()和workGroup.shutdownGracefully()，改为链路关闭时在释放调用和连接句柄
     *
     */
    public void start2() throws InterruptedException {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap b=new ServerBootstrap();
        b.group(bossGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,100)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler((new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(new LoggingHandler(LogLevel.INFO));
                        p.addLast(new StringEncoder());
                        p.addLast(new StringDecoder());
                    }
                }));

        //b.bind(18080).sync();//同步方式绑定服务断开
        ChannelFuture cf = b.bind(18080).sync();
        //监听Close Future ，还会发生服务器套接字直接关系，进程退出的问题
        cf.channel().closeFuture().addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                bossGroup.shutdownGracefully();
                workGroup.shutdownGracefully();
                log.info(future.channel().toString()+"链路关闭");
            }
        });
        //cf.channel().closeFuture().sync();
    }

    /**
     * 如何防止Netty服务端意味退出
     * 1.程序监听NioServerSocketChannel的关闭事件并同步阻塞main函数，我们对服务端代码进行更改
     */
    public void start3() throws InterruptedException {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap b=new ServerBootstrap();
            b.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler((new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new LoggingHandler(LogLevel.INFO));
                            p.addLast(new StringEncoder());
                            p.addLast(new StringDecoder());
                        }
                    }));

            //b.bind(18080).sync();//同步方式绑定服务断开
            ChannelFuture cf = b.bind(18080).sync();
            //监听Close Future ，还会发生服务器套接字直接关系，进程退出的问题
//            cf.channel().closeFuture().addListener(new ChannelFutureListener() {
//                @Override
//                public void operationComplete(ChannelFuture future) throws Exception {
//                    log.info(future.channel().toString()+"链路关闭");
//                }
//            });
            cf.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    /**
     * 地洞Netty服务端比较容易犯的错误时采用同步的方式调用Netty，导致初始化Netty服务端的业务线程都被阻塞
     * 1.初始化Netty服务端
     * 2.同步阻塞等待服务端口关闭
     * 3.释放I/O线程资源和句柄等
     * 4.调用方线程被释放
     * 正确用法，服务端启动之后注册监听服务端句柄关闭事件，待服务端关闭之后，异步调用shutdownGracefully释放资源
     * ，这样调用方法可以快速返回，不会被阻塞
     * 初始化Netty服务端
     * 绑定监听端口
     * 向CloseFuture注册监听器，子啊监听器中释放资源
     * 调用方线程返回
     */

    /**
     * Netty优雅退出机制
     * 在linux上通常会通过kill -9 pid的方式强制将某个进程杀掉，这方方式简单高效
     * 无论是Linux的Kill -9 pid还是windows的taskKill/f/pid强制退出进程，都会带来一些副作用，对应用软件而言其效果等同于突然断电
     * 1.缓存中的数据尚未持久化到磁盘，导致数据丢失
     * 2，正在进行文件的写(write)操作，没有更新完成，突然退出，导致文件损坏
     * 3，线程消息队列中尚有接收到的请求消息还没有来得及处理，导致请求消息丢失
     * 4，数据库操作已经完成，例如账号余额更新，准备返回应答消息给客户端时，消息尚在通信线程得发送队列中排队等待发送，进程强制退出导致应答消息没有返回给客户端
     * 客户端发起超时重试，会带来重复更新问题
     * 5.句柄资源没有及时释放等其他问题
     */

    public static void main(String[]args) throws InterruptedException {
        long startTime=System.nanoTime();
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            try{
                log.info("程序开始执行");
                TimeUnit.SECONDS.sleep(3);
                log.info("程序结束执行");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"Daemon-t1"));
        TimeUnit.SECONDS.sleep(7);
        log.info("系统退出，程序执行:{}秒",(System.nanoTime()-startTime)/1000/1000/100);
        System.exit(0);
    }

    /**
     * java优雅退出得注意点
     * 通过ShutdownHook上西安优雅需要注意几点
     * 1.ShutdownHook在某些情况下并不会被执行，例如JVM崩溃，无法接收信号量和kill -9 pid
     * 2.当存在多个ShutdownHook时，JVM无法保证它们得执行先后顺序
     * 3.在JVM关闭期间不能动态添加或者去除ShutdownHook
     * 4.不能在ShutdownHook中调用System.exit(),它会卡住JVM，导致进程无法退出
     */
}
