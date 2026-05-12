package com.tools.desgin.behavior.Memento.node01;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2024/10/10 16:36
 */
public class CareTaker {
    private final List<Memento> mementos=new ArrayList<>();

    public void add(Memento state){
        mementos.add(state);
    }

    public Memento get(int index){
        return mementos.get(index);
    }
}
