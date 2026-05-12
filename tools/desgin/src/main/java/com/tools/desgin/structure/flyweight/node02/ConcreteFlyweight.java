package com.tools.desgin.structure.flyweight.node02;

import java.util.StringJoiner;

/**
 * @author shenlx
 * @description
 * @date 2024/9/10 14:33
 */
public class ConcreteFlyweight implements Flyweight{
    private Character innerState=null;

    public ConcreteFlyweight(Character state){
        this.innerState=state;
    }

    @Override
    public void printState(String state) {
        System.out.println("内部状态："+this.innerState);
        System.out.println("外部状态: "+state);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ConcreteFlyweight.class.getSimpleName() + "[", "]")
                .add("innerState=" + innerState)
                .toString();
    }
}
