package com.tools.desgin.behavior.iterator.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/10/11 16:04
 */
public class ConcreteAggregate extends Aggregate{
    private Object[] objArray=null;

    public ConcreteAggregate(Object[] objArray) {
        this.objArray = objArray;
    }

    @Override
    public Iterator createIterator() {
        return new ConcreteIterator(this);
    }

    public Object getElement(int index){
        if(index<objArray.length){
            return objArray[index];
        }else {
            return null;
        }
    }

        public int getSize(){
        return objArray.length;
    }
}
