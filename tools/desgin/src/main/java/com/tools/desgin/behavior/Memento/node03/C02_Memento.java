package com.tools.desgin.behavior.Memento.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/10/11 10:49
 */
public class C02_Memento {
    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();
        originator.setState("状态1:State01");
        caretaker.add(originator.saveStateMemento());
        originator.setState("状态2:State02");
        caretaker.add(originator.saveStateMemento());
        System.out.println("当前的状态是 =" + originator.getState());
        // 恢复状态
        originator.getStateFromMemento(caretaker.get(0));
        System.out.println("当前的状态是 =" + originator.getState());
    }
}
