package com.tools.desgin.advanced.factory.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/8/14 14:39
 */
public class Circle implements Shape{
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
