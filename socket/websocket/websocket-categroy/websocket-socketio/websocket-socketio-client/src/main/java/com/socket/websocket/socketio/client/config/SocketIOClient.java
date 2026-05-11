package com.socket.websocket.socketio.client.config;

import io.socket.client.Ack;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.net.URISyntaxException;

/**
 * @author shenlx
 * @description
 * @date 2026/5/11 15:35
 */
public class SocketIOClient {
    public void connect() throws URISyntaxException {
        //SSL (HTTPS, WSS) 设置：
        IO.Options opts = new IO.Options();
        opts.forceNew = true;
        opts.reconnection = false;
        //String authToken="";
        //opts.query = "auth_token=" + authToken;
        Socket socket = IO.socket("http://localhost",opts);
        //SSL (HTTPS, WSS) 设置：
        // default settings for all sockets
//        IO.setDefaultSSLContext(mySSLContext);
//        IO.setDefaultHostnameVerifier(myHostnameVerifier);
//
//        // set as an option
//        opts = new IO.Options();
//        opts.sslContext = mySSLContext;
//        opts.hostnameVerifier = myHostnameVerifier;
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                //当服务器接收到一个消息时，可以用 Ack 获取一个回调：
                socket.emit("foo", "hi", new Ack() {
                    @Override
                    public void call(Object... objects) {

                    }
                });
                //socket.disconnect();
            }

        }).on(io.socket.engineio.client.Socket.EVENT_MESSAGE, new Emitter.Listener() {

            @Override
            public void call(Object... args) {}

        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {}

        });
        socket.connect();
    }
}
