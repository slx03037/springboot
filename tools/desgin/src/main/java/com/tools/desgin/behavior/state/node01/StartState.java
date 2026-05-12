package com.tools.desgin.behavior.state.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/10/11 16:45
 */
public class StartState implements State{
    @Override
    public void doAction(Context context) {
        System.out.println("Player is in start state");
        context.setState(this);
    }

    @Override
    public String toString(){
        return "Start state";
    }
}
