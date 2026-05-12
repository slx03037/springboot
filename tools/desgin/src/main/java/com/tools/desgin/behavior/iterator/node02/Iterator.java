package com.tools.desgin.behavior.iterator.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/10/11 16:02
 */
public interface Iterator {
    void first();
    void next();
    boolean isEnd();
    Object currentItem();
}
