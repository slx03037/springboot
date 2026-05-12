package com.tools.desgin.behavior.command.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/10/9 15:30
 */
public class Stock {
    private final String name="ABC";
    private final int quantity=10;

    public void buy(){
        System.out.println("Stock [ name:"+name+", Quantity:"+quantity+"] bought");

    }

    public void sell(){
        System.out.println("Stock [ name:"+name+",Quantity:"+quantity+"] sold");
    }
}
