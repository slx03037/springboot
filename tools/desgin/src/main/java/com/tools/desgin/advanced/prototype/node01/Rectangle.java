package com.tools.desgin.advanced.prototype.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/8/16 10:52
 */
public class Rectangle extends Shape{
    public Rectangle(){
        type="Rectangle";
    }
    @Override
    void draw() {
       System.out.println("Inside Rectangle: draw() method.");
    }
}
