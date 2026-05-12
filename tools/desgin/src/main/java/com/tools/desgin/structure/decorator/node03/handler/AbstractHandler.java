package com.tools.desgin.structure.decorator.node03.handler;

/**
 * @author shenlx
 * @description
 * @date 2024/8/30 10:12
 */
public abstract class AbstractHandler <T,R> implements BaseHandler<T,R>{
    protected BaseHandler baseHandler;

    public void setService(BaseHandler service){
        this.baseHandler=service;
    }
}
