package com.tools.desgin.behavior.chainofresponsibility.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/10/12 16:16
 */
public class BHandler extends BaseRequestHandler{
    @Override
    public void doHandler(String req) {
        System.out.println("B 中处理自己的逻辑");

        if(next !=null){
            next.doHandler(req);
        }
    }
}
