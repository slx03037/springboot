package com.tools.desgin.structure.composite.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/9/3 15:55
 */
public class Leaf implements Component{
    private final String name;

    public Leaf(String name){
        this.name=name;
    }

    @Override
    public void printStruct(String preStr) {
        System.out.println(preStr+"_"+name);
    }
}
