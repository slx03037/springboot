package com.tools.desgin.behavior.observer.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/9/11 14:01
 */
public abstract class Observer {
    protected Subject subject;

    public abstract  void update();
}
