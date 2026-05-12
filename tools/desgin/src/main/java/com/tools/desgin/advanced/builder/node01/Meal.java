package com.tools.desgin.advanced.builder.node01;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2024/8/21 10:53
 */
public class Meal {
    private final List<Item> items=new ArrayList<>();

    public void addItem(Item item){
        items.add(item);
    }

    public float getCost(){
        float cost=0.0f;

        for (Item item:items){
            cost+=item.price();
        }

        return cost;
    }

    public void showItems(){
        for(Item item:items){
            System.out.println("Item:"+item.name()+" , Packing:"+item.packing().pack()+", Price:"+item.price());
        }
    }
}
