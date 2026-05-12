package com.tools.desgin.behavior.Memento.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/10/11 10:48
 */
public class Originator {
    private String state;
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public Memento saveStateMemento() {
        return new Memento(state);
    }
    public void getStateFromMemento(Memento memento) {
        state = memento.getState();
    }
}
