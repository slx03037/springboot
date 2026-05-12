package com.tools.desgin.behavior.visitor.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/10/14 9:43
 */
public class VisitorPatternDemo {
    public static void main(String[]args){
        ComputerPart computer=new Computer();

        computer.accept(new ComputerDisplayVisitor());
    }
}
