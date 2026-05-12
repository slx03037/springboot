package com.tools.desgin.structure.composite.node03;

import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2024/9/9 15:55
 */
public abstract class Component {
    public abstract void  printStruct(String preStr);

    public void addChild(Component child){
        throw  new UnsupportedOperationException("对象不支持此功能");
    }

    public void removeChild(int index){
        throw  new UnsupportedOperationException("对象不支持此功能");
    }

    public List<Component> getChild(){
        throw  new UnsupportedOperationException("对象不支持此功能");
    }

}
