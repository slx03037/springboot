package com.tools.desgin.behavior.chainofresponsibility.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/10/12 15:30
 */
public class ErrorLogger extends AbstractLogger{

    public ErrorLogger(int level){
        this.level=level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error Console:Logger"+message);
    }
}
