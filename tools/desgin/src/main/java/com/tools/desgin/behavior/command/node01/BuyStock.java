package com.tools.desgin.behavior.command.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/10/9 15:33
 */
public class BuyStock implements Order{
    private final Stock abcStock;

    public BuyStock(Stock abcStock){
        this.abcStock=abcStock;
    }

    @Override
    public void execute() {
        abcStock.buy();
    }
}
