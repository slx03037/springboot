package com.tools.desgin.behavior.chainofresponsibility.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/10/12 15:31
 */
public class FileLogger extends AbstractLogger{

    public FileLogger(int level){
        this.level=level;
    }

    @Override
    protected void write(String message) {
        System.out.println("File :Logger"+message);
    }
}
