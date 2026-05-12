package com.tools.desgin.spring.test02;

/**
 * @author shenlx
 * @description
 * @date 2024/8/19 13:55
 */
public class Sheep {
    private String name ;
    private String color ;

    @Override
    public String toString() {
        return "Sheep{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
