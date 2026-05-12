package com.tools.desgin.behavior.command.node01;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2024/10/9 15:35
 */
public class Broker {

    private final List<Order> orderList=new ArrayList<>();

    public void takeOrder(Order order){
        orderList.add(order);
    }

    public void placeOrders(){
        for(Order order:orderList){
            order.execute();
        }
        orderList.clear();
    }
}
