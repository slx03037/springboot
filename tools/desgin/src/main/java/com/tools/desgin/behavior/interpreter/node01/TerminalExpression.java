package com.tools.desgin.behavior.interpreter.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/10/11 11:15
 */
public class TerminalExpression implements Expression{
    private final String  data;
    @Override
    public boolean interpret(String context) {
        return context.contains(data);
    }

    public TerminalExpression(String data) {
        this.data = data;
    }
}
