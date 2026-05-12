package com.tools.desgin.advanced.factory.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/8/14 15:15
 */
public class Order_Food {
    public Food orderFood(String foodName){
        Food food=null;

        if(foodName.equals("fish")){
            food=new Fish_Food();
            food.setName("鲈鱼");
        }else if(foodName.equals("chicken")){
            food=new Chicken_Food();
            food.setName("小土鸡");
        }
        if(food ==null) {
            return null;
        }

        food.foodMaterial();
        food.cookFood();
        return food;

    }
}
