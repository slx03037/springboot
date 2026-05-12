package com.tools.desgin.behavior.interpreter.node02;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shenlx
 * @description
 * @date 2024/10/11 11:24
 */
public class DataMap {
    private final Map<Express,Integer> dataMap=new HashMap<>();

    public void putData(Express key,Integer value){
        dataMap.put(key,value);
    }

    public Integer getData(Express key){
        return dataMap.get(key);
    }
}
