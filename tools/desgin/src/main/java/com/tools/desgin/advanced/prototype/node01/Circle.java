package com.tools.desgin.advanced.prototype.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/8/16 10:54
 */
public class Circle extends Shape{
    public Circle(){
        type="Circle";
    }
    @Override
    void draw() {
        System.out.println("Inside Circle: draw() method.");
    }
}
