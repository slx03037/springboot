package com.tools.desgin.behavior.chainofresponsibility.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/10/12 16:16
 */
public class Client {

    public static void main(String[]args){
        BaseRequestHandler a=new AHandler();
        BaseRequestHandler b=new BHandler();
        BaseRequestHandler c=new CHandler();
        a.next(b);
        b.next(c);
        a.doHandler("链路待处理数据");
    }
}
