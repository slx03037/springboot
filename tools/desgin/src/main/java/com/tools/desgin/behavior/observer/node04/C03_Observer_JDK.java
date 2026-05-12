package com.tools.desgin.behavior.observer.node04;

/**
 * @author shenlx
 * @description
 * @date 2024/9/11 17:30
 */
public class C03_Observer_JDK {
    public static void main(String[]args){
        MsgSource msgSource = new MsgSource();

        MsgConsumer watch = new MsgConsumer(msgSource);

        msgSource.setData("hello.java");

        msgSource.setData("bye.java");
    }
}
