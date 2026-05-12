package com.tools.desgin.behavior.chainofresponsibility.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/10/12 16:28
 */
public interface IHandler<T,R> {

    R handler(T t);
}
