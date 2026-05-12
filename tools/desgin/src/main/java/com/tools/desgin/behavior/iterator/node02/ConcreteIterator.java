package com.tools.desgin.behavior.iterator.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/10/11 16:03
 */
public class ConcreteIterator implements Iterator{

    private final ConcreteAggregate aggregate;

    private int index=0;

    private int size=0;

    public ConcreteIterator(ConcreteAggregate aggregate) {
        this.aggregate = aggregate;
        this.size=aggregate.getSize();
        index=0;
    }

    @Override
    public void first() {
        index=0;
    }

    @Override
    public void next() {
        if(index<size){
            index++;
        }
    }

    @Override
    public boolean isEnd() {
        return (index>=size);
    }

    @Override
    public Object currentItem() {
        return aggregate.getElement(index);
    }
}
