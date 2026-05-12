package com.tools.desgin.structure.decorator.node03.handler;

/**
 * @author shenlx
 * @description
 * @date 2024/8/30 10:11
 */
public interface BaseHandler <T,R>{
    /**
     * 统一的处理方法
     */
    R handler(T t);
}
