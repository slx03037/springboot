package com.tools.desgin.behavior.visitor.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/10/14 10:27
 */
public interface Visitor {
    void visit(NodeA nodeA);

    void visit(NodeB nodeB);
}
