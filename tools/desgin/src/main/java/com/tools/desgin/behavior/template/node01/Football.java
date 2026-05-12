package com.tools.desgin.behavior.template.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/9/24 11:38
 */
public class Football extends Game{
    @Override
    void initialize() {
        System.out.println("Football Game initialized Start Playing");
    }

    @Override
    void startPlay() {
        System.out.println("Football Game  Started Enjoy the Game");
    }

    @Override
    void endPlay() {
        System.out.println("Football Game  Finished");
    }
}
