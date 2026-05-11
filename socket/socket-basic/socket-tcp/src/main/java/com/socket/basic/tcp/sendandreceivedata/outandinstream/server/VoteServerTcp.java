package com.socket.basic.tcp.sendandreceivedata.outandinstream.server;


import com.socket.basic.tcp.sendandreceivedata.outandinstream.Framer;
import com.socket.basic.tcp.sendandreceivedata.outandinstream.LengthFramer;
import com.socket.basic.tcp.sendandreceivedata.outandinstream.VoteMsg;
import com.socket.basic.tcp.sendandreceivedata.outandinstream.VoteMsgBinCoder;
import com.socket.basic.tcp.sendandreceivedata.outandinstream.service.VoteService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author shenlx
 * @description
 * @date 2024/1/19 10:38
 */
public class VoteServerTcp {
    private final static int port=8989;

    public static void main(String[]args) throws IOException {
        ServerSocket socket = new ServerSocket(port);

        VoteMsgBinCoder coder = new VoteMsgBinCoder();

        VoteService voteService = new VoteService();

        while(true){
            Socket accept = socket.accept();

            System.out.println("Handling client at"+accept.getRemoteSocketAddress());

            Framer framer=new LengthFramer(accept.getInputStream());

            try{
                byte [] req;

                while((req=framer.nextMsg())!=null){
                    System.out.println("Received message("+req.length+"bytes");

                    VoteMsg msg = voteService.HandlerRequest(coder.fromWire(req));

                    framer.frameMsg(coder.towire(msg),accept.getOutputStream());
                }
            }catch (IOException e){
                System.err.println("Error handling client: "+e.getMessage());
            }finally {
                System.out.println("Closing connection");
                accept.close();
            }
        }

    }


}
