package com.tools.desgin.behavior.state.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/10/11 16:59
 */
public class C01_InScene {
    public static void main(String[]args){
        Chameleon chameleon = new Chameleon("红色", "花丛环境");

        LifeContext lifeContext = new LifeContext();

        BodyColor bodyColor = new GreenColor();

        lifeContext.setBodyColor(bodyColor);
        lifeContext.change(chameleon);

        bodyColor =new GrayColor();
        lifeContext.setBodyColor(bodyColor);
        lifeContext.change(chameleon);
    }
}
