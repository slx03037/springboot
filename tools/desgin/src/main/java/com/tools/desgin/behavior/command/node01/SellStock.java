package com.tools.desgin.behavior.command.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/10/9 15:34
 */
public class SellStock implements Order{

    private final Stock abcStock;

    public SellStock(Stock abcStock){
        this.abcStock=abcStock;
    }

    @Override
    public void execute() {
        abcStock.sell();
    }
}
