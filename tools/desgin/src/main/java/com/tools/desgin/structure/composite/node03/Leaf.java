package com.tools.desgin.structure.composite.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/9/9 16:01
 */
public class Leaf extends Component{
    private final String name;

    public Leaf(String name){
        this.name=name;
    }

    @Override
    public void printStruct(String preStr) {
        System.out.println(preStr+"_"+name);
    }
}
