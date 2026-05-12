package com.tools.desgin.behavior.visitor.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/10/14 9:38
 */
public class Computer implements ComputerPart{
    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        for(int i=0;i<parts.length;i++){
            parts[i].accept(computerPartVisitor);
        }
        computerPartVisitor.visit(this);
    }

    ComputerPart[]parts;

    public Computer(){
        parts=new ComputerPart[]{new Mouse(),new Keyboard(),new Mouse()};
    }
}
