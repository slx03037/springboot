package com.tools.desgin.structure.flyweight.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/9/9 17:27
 */
public class FlyWeightPatternDemo {
    private static final String[] colors={"red","green","blue","white","black"};

    public static void main(String[]args){
        for(int i=0; i<=20;i++){
            Circle circle = (Circle) ShareFactory.getCircle(getRandomColor());
            circle.setX(getRandomX());
            circle.setY(getRandomY());
            circle.setRadius(100);
            circle.draw();
        }
    }

    private static String getRandomColor(){
        return colors[(int)(Math.random()*colors.length)];
    }

    private static int getRandomX(){
        return (int)(Math.random()*100);
    }

    private static int getRandomY(){
        return (int)(Math.random()*100);
    }

}
