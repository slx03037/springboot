package com.tools.desgin.behavior.chainofresponsibility.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/10/12 16:14
 */
public abstract class BaseRequestHandler implements RequestHandler{

    protected RequestHandler next;

    public void next(RequestHandler next){
        this.next=next;
    }
}
