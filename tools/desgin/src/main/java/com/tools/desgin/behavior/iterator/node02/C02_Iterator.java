package com.tools.desgin.behavior.iterator.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/10/11 16:08
 */
public class C02_Iterator {

    public static void main(String[]args){
        Object[] objArray={"one","two","three","four","five"};
        ConcreteAggregate concreteAggregate = new ConcreteAggregate(objArray);
        Iterator iterator = concreteAggregate.createIterator();
        while(!iterator.isEnd()){
            System.out.println(iterator.currentItem());
            iterator.next();
        }

    }
}
