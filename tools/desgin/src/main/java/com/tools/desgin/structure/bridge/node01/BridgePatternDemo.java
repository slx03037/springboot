package com.tools.desgin.structure.bridge.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/9/10 16:11
 */
public class BridgePatternDemo {

    public static void main(String[]args){
        Shape redCircle = new Circle(100, 100, 10, new RedCircle());
        Shape greenCircle = new Circle(100, 100, 10, new GreenCircle());

        redCircle.draw();
        greenCircle.draw();
    }
}
