package com.tools.desgin.structure.flyweight.node02;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shenlx
 * @description
 * @date 2024/9/10 14:35
 */
public class FlyweightFactory {
    private final Map<Character,Flyweight> pool=new HashMap<>();

    public Flyweight factoryMethod(Character state){
        Flyweight fly=pool.get(state);
        if(fly==null){
            fly=new ConcreteFlyweight(state);
            pool.put(state,fly);
        }
        return fly;
    }

    public void poolInfo(){
        System.out.println("数据池:"+pool);
    }
}
