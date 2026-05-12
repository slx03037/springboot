package com.tools.desgin.advanced.factory.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/8/14 14:37
 */
public class Square implements Shape{
    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
