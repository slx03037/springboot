package com.tools.desgin.behavior.iterator.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/10/11 15:59
 */
public class IteratorPatternDemo {
    public static void main(String[]args){
        NameRepository nameRepository=new NameRepository();

        for(Iterator iterator= nameRepository.getIterator(); iterator.hasNext();){
            String name=(String) iterator.next();
            System.out.println("Name:"+name);
        }

    }
}
