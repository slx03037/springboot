package com.tools.desgin.behavior.template.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/9/24 11:39
 */
public class TemplatePatternDemo {
    public static void main(String[]args){
        Game game=new Cricket();
        game.play();
        System.out.println(game.hashCode());
        game=new Football();
        game.play();
        System.out.println(game.hashCode());
    }

}
