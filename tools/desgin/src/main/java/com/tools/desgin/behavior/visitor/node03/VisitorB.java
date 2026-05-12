package com.tools.desgin.behavior.visitor.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/10/14 10:32
 */
public class VisitorB implements Visitor{

    @Override
    public void visit(NodeA nodeA) {
        nodeA.operationA();
    }

    @Override
    public void visit(NodeB nodeB) {
        nodeB.operationB();
    }
}
