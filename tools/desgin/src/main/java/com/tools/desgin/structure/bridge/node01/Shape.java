package com.tools.desgin.structure.bridge.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/9/10 16:08
 */
public abstract class Shape {
    protected DrawAPI drawAPI;

    protected Shape(DrawAPI drawAPI){
        this.drawAPI=drawAPI;
    }

    public abstract void draw();
}
