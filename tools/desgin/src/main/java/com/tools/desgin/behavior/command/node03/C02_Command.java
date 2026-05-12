package com.tools.desgin.behavior.command.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/10/9 16:50
 */
public class C02_Command {

    public static void main(String[]args){
        Receiver receiver = new Receiver();

        Command concreteCommand = new ConcreteCommand(receiver);

        Invoker invoker = new Invoker(concreteCommand);

        invoker.action("卧倒");
    }
}
