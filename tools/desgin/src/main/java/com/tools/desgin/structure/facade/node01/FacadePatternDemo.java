package com.tools.desgin.structure.facade.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/8/23 11:05
 */
public class FacadePatternDemo {
    public static void main(String[]args){
        ShapeMaker shapeMaker = new ShapeMaker();

        shapeMaker.drawCircle();
        shapeMaker.rectangleCircle();
        shapeMaker.squareCircle();
    }
}
