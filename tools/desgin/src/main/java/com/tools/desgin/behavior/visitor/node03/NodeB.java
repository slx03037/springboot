package com.tools.desgin.behavior.visitor.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/10/14 10:27
 */
public class NodeB extends Node{
    @Override
    void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void operationB(){
        System.out.println("NodeB.operationB");
    }
}
