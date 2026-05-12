package com.tools.desgin.behavior.command.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/10/9 16:47
 */
public class ConcreteCommand implements Command{
    private Receiver receiver=null;

    public ConcreteCommand(Receiver receiver){
        this.receiver=receiver;
    }

    @Override
    public void execute(String task) {
        receiver.action(task);
    }
}
