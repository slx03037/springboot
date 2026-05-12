package com.tools.desgin.behavior.state.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/10/11 17:12
 */
public class ConcreteStateA implements State{
    @Override
    public void stateInfo(String param) {
        System.out.println("ConcreteStateA:"+param);
    }
}
