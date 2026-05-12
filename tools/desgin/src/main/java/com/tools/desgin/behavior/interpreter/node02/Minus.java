package com.tools.desgin.behavior.interpreter.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/10/11 11:29
 */
public class Minus extends NotTerminalExpress{
    public Minus(Express express1, Express express2) {
        super(express1, express2);
    }

    @Override
    public Integer interpret(DataMap dataMap) {
        return this.express1.interpret(dataMap) - this.express2.interpret(dataMap);
    }
}
