package com.tools.desgin.advanced.factory.node05;

/**
 * @author shenlx
 * @description
 * @date 2024/8/15 16:54
 */
public abstract class AbstractFactory {
    public abstract Color getColor(String color);

    public abstract Shape getShape(String shape);
}
