package com.tools.desgin.structure.bridge.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/9/10 16:07
 */
public class RedCircle implements DrawAPI{
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing circle[color:red,radius:"+radius+",x:"+x+",y:"+y+"]");
    }
}
