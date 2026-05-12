package com.tools.desgin.behavior.iterator.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/10/11 15:56
 */
public class NameRepository implements Container{

    public String[] names={"Robert","John","Julie","Lora"};

    @Override
    public Iterator getIterator() {
        return new NameIterator();
    }


    private class NameIterator implements Iterator{

        int index;

        @Override
        public boolean hasNext() {
            return index < names.length;
        }

        @Override
        public Object next() {
            if(this.hasNext()){
                return names[index++];
            }
            return null;
        }
    }
}
