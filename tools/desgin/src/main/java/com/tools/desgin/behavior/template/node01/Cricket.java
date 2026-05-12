package com.tools.desgin.behavior.template.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/9/24 11:37
 */
public class Cricket extends Game{
    @Override
    void initialize() {
        System.out.println("Cricket Game initialized Start Playing");
    }

    @Override
    void startPlay() {
        System.out.println("Cricket Game  Started Enjoy the Game");
    }

    @Override
    void endPlay() {
        System.out.println("Cricket Game  Finished");
    }
}
