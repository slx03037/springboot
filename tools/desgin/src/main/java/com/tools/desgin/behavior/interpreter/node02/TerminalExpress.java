package com.tools.desgin.behavior.interpreter.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/10/11 11:28
 */
public class TerminalExpress implements Express{

    public String field;

    public TerminalExpress(String field) {
        this.field = field;
    }

    @Override
    public Integer interpret(DataMap dataMap) {
        return null;
    }
}
