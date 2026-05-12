package com.tools.desgin.behavior.visitor.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/10/14 10:27
 */
public class NodeA extends Node{
    @Override
    void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void operationA(){
        System.out.println("NodeA operationA");
    }
}
