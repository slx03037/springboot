package com.tools.desgin.structure.composite.node02;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2024/9/3 15:35
 */
public class Composite implements Component{
    private final List<Component> childComponents=new ArrayList<>();

    private final String name;

    public Composite(String name){
        this.name=name;
    }

    public void addChild(Component child){
        childComponents.add(child);
    }

    public void removeChild(int index){
        childComponents.remove(index);
    }

    public List<Component> getChild(){
        return childComponents;
    }


    @Override
    public void printStruct(String preStr) {
        System.out.println(preStr+"+"+this.name);

        if(this.childComponents !=null){
            preStr=preStr+" ";
            for(Component c:childComponents){
                c.printStruct(preStr);
            }
        }
    }
}
