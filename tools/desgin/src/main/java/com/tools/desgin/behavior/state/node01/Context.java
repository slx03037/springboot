package com.tools.desgin.behavior.state.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/10/11 16:44
 */
public class Context {
    private State state;

    public Context(){
        state=null;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
