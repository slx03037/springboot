package com.tools.desgin.behavior.visitor.node01;


/**
 * @author shenlx
 * @description
 * @date 2024/10/12 17:29
 */
public interface ComputerPartVisitor {

    void visit(Computer computer);
    void visit(Mouse mouse);
    void visit(Keyboard keyboard);
    void visit(Monitor monitor);
}
