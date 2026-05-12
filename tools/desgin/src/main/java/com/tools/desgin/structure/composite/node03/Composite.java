package com.tools.desgin.structure.composite.node03;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2024/9/9 15:57
 */
public class Composite extends Component{
    private final List<Component> childComponents=new ArrayList<>();

    private final String name;

    public Composite(String name){
        this.name=name;
    }

    @Override
    public void printStruct(String preStr) {
        System.out.println(preStr+"+"+this.name);
        preStr=preStr+" ";
        if(this.childComponents !=null){
            for(Component c:childComponents){
                c.printStruct(preStr);
            }
        }
    }

    @Override
    public void addChild(Component child){
        childComponents.add(child);
    }

    @Override
    public void removeChild(int index){
        childComponents.remove(index);
    }

    @Override
    public List<Component> getChild(){
        return childComponents;
    }
}
