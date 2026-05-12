package com.tools.desgin.behavior.Memento.node03;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2024/10/11 10:48
 */
public class Caretaker {
    private final List<Memento> mementoList = new ArrayList<>();
    public void add(Memento memento) {
        mementoList.add(memento);
    }
    public Memento get (int index) {
        return mementoList.get(index);
    }
}
