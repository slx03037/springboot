package com.tools.desgin.behavior.visitor.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/10/12 17:28
 */
public interface ComputerPart {

    void accept(ComputerPartVisitor computerPartVisitor);
}
