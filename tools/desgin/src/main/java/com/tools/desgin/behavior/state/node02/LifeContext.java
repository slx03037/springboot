package com.tools.desgin.behavior.state.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/10/11 16:55
 */
public class LifeContext {
    private BodyColor bodyColor;

    public void setBodyColor(BodyColor bodyColor) {
        this.bodyColor = bodyColor;
    }

    public void change(Chameleon chameleon){
        bodyColor.change(chameleon);
    }
}
