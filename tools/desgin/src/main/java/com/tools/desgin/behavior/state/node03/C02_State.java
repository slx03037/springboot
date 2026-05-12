package com.tools.desgin.behavior.state.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/10/11 17:14
 */
public class C02_State {
    public static void main(String[]args){
        Context context = new Context();

        State state=new ConcreteStateA();
        context.setState(state);
        context.printInfo("当前状态A");

        state=new ConcreteStateB();
        context.setState(state);
        context.printInfo("当前环境状态B");

    }
}
