package com.tools.desgin.behavior.state.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/10/11 17:13
 */
public class Context {
    private State state;

    public void setState(State state) {
        this.state = state;
    }

    public void printInfo(String info){
        state.stateInfo(info);
    }
}
