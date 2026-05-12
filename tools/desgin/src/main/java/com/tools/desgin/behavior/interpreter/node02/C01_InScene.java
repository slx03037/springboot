package com.tools.desgin.behavior.interpreter.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/10/11 11:30
 */
public class C01_InScene {

    public static void main(String[]args){
        DataMap dataMap = new DataMap();

        TerminalExpress num1 = new TerminalExpress("num1");

        TerminalExpress num2 = new TerminalExpress("num2");

        TerminalExpress num3 = new TerminalExpress("num3");

        dataMap.putData(num1,1);

        dataMap.putData(num2,2);

        dataMap.putData(num3,3);

        System.out.println(new Minus(new Add(num1,num2),num3).interpret(dataMap));
    }
}
