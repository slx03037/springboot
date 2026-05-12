package com.tools.desgin.advanced.factory.node06;

import org.apache.naming.factory.BeanFactory;

import javax.annotation.Resource;

/**
 * @author shenlx
 * @description
 * @date 2024/8/15 17:24
 */
public class C01_AbstractFactory {
    @Resource
    private BeanFactory beanFactory;
    public static void main(String[]args){
        CarProductFactory product = new ChinaCarFactory();
        product.getCar("df");
        product=new GermanyCarFactory();
        product.getCar("ad");
    }
}
