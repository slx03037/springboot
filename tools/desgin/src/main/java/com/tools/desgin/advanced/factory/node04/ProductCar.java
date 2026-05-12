package com.tools.desgin.advanced.factory.node04;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shenlx
 * @description
 * @date 2024/8/15 15:27
 */
public class ProductCar implements CarFactory{

    @Override
    public CarEntity getCar(String type) {
        return carMap.get(type);
    }

    private Map<String,CarEntity> carMap=null;

    public ProductCar(){
        carMap=new HashMap<>();
        carMap.put("china",new CarEntity("中国","黑色","比亚迪"));
        carMap.put("america",new CarEntity("美国","黑色","特斯拉"));
    }
}
