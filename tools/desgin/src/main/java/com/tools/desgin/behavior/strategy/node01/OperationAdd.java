package com.tools.desgin.behavior.strategy.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/10/8 15:34
 */
public class OperationAdd implements Strategy{
    @Override
    public int doOperation(int num1, int num2) {
        return num1+num2;
    }
}
