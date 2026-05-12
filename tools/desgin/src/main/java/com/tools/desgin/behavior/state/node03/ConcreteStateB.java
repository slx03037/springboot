package com.tools.desgin.behavior.state.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/10/11 17:13
 */
public class ConcreteStateB implements State{
    @Override
    public void stateInfo(String param) {
        System.out.println("ConcreteStateB:"+param);
    }
}
