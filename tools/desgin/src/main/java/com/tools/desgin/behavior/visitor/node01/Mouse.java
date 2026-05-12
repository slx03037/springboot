package com.tools.desgin.behavior.visitor.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/10/14 9:39
 */
public class Mouse implements ComputerPart{
    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}
