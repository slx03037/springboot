package com.tools.desgin.structure.bridge.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/9/10 16:08
 */
public class GreenCircle implements DrawAPI{
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing circle[color:green,radius:"+radius+",x:"+x+",y:"+y+"]");
    }
}
