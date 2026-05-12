package com.tools.desgin.behavior.state.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/10/11 16:46
 */
public class StopState implements State{
    @Override
    public void doAction(Context context) {
        System.out.println("Player is in stop state");
        context.setState(this);
    }

    @Override
    public String toString(){
        return "stop state";
    }
}
