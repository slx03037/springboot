package com.tools.desgin.structure.flyweight.node01;

import java.util.HashMap;

/**
 * @author shenlx
 * @description
 * @date 2024/9/9 17:14
 */
public class ShareFactory {
    private static final HashMap<String,Shape> circleMap=new HashMap<>();

    public static Shape getCircle(String color){
        Circle circle = (Circle)circleMap.get(color);

        if(circle ==null){
            circle=new Circle(color);
            circleMap.put(color,circle);
            System.out.println("Creating circle of color:"+color);
        }
        return circle;
    }
}
