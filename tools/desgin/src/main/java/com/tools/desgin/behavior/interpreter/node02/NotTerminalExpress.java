package com.tools.desgin.behavior.interpreter.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/10/11 11:27
 */
public abstract class NotTerminalExpress implements Express{
    Express express1;

    Express express2;

    public NotTerminalExpress(Express express1, Express express2) {
        this.express1 = express1;
        this.express2 = express2;
    }
}
