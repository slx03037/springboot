package com.tools.desgin.advanced.builder.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/8/21 10:59
 */
public class BuilderPatternDemo {
    public static void main(String[]args){
        MealBuilder mealBuilder = new MealBuilder();

        Meal meal = mealBuilder.prepareVegMeal();
        meal.showItems();
        System.out.println("Total cost"+meal.getCost());
        System.out.println("----------<<<<<<<<<>>>>>>>>>>>>>>>>----------");
        Meal meal1 = mealBuilder.prepareNonVegMeal();
        meal1.showItems();
        System.out.println("Total cost"+meal1.getCost());
    }
}
