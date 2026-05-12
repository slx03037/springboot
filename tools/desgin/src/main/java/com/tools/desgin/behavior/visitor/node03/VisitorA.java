package com.tools.desgin.behavior.visitor.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/10/14 10:28
 */
public class VisitorA implements Visitor{
    @Override
    public void visit(NodeA nodeA) {
        nodeA.operationA();
    }

    @Override
    public void visit(NodeB nodeB) {
        nodeB.operationB();
    }
}
