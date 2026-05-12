package com.tools.desgin.advanced.prototype.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/8/16 10:53
 */
public class Square extends Shape{
    public Square(){
        type="Square";
    }
    @Override
    void draw() {
        System.out.println("Inside Square:draw() method.");
    }
}
