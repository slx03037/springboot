package com.tools.desgin.behavior.visitor.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/10/14 9:42
 */
public class ComputerDisplayVisitor implements ComputerPartVisitor{
    @Override
    public void visit(Computer computer) {
        System.out.println("Displaying Computer");
    }

    @Override
    public void visit(Mouse mouse) {
        System.out.println("Displaying Mouse");
    }

    @Override
    public void visit(Keyboard keyboard) {
        System.out.println("Displaying Keyboard");
    }

    @Override
    public void visit(Monitor monitor) {
        System.out.println("Displaying Monitor");
    }
}
