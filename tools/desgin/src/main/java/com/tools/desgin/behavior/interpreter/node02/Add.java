package com.tools.desgin.behavior.interpreter.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/10/11 11:28
 */
public class Add extends NotTerminalExpress{


    public Add(Express express1, Express express2) {
        super(express1, express2);
    }

    @Override
    public Integer interpret(DataMap dataMap) {
        return this.express1.interpret(dataMap)+this.express2.interpret(dataMap);
    }
}
