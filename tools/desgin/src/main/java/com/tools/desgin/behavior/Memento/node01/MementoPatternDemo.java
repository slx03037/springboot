package com.tools.desgin.behavior.Memento.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/10/10 16:38
 */
public class MementoPatternDemo {
    public static void main(String[]args){
        Originator originator=new Originator();

        CareTaker careTaker=new CareTaker();

        originator.setState("State #1");
        originator.setState("State #2");
        careTaker.add(originator.saveStateToMemento());
        originator.setState("State #3");
        careTaker.add(originator.saveStateToMemento());
        originator.setState("State #4");
        careTaker.add(originator.saveStateToMemento());

        System.out.println("Current State:"+originator.getState());
        originator.getStateFromMemento(careTaker.get(0));
        System.out.println("First save state:"+originator.getState());
        originator.getStateFromMemento(careTaker.get(1));
        System.out.println("Second save state:"+originator.getState());
    }
}
