package com.tools.desgin.behavior.command.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/10/9 16:47
 */
public class Invoker {

    private final Command command;

    public Invoker(Command command){
        this.command=command;
    }

    public void action(String task){
        command.execute(task);
    }
}
