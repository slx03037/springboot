package com.tools.desgin.behavior.visitor.node03;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2024/10/14 10:29
 */
public class ObjectStructure {

    private final List<Node> nodes=new ArrayList<>();

    public void detach(Node node){
        nodes.add(node);
    }

    public void add(Node node){
        nodes.add(node);
    }

    public void doAccept(Visitor visitor){
        for(Node node:nodes){
            node.accept(visitor);
        }
    }
}
