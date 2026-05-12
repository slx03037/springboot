package com.tools.desgin.advanced.prototype.node03;

import java.io.*;

/**
 * @author shenlx
 * @description
 * @date 2024/8/19 14:17
 */
public class Dog implements Cloneable, Serializable {
    private static final long serialVersionUID = -7784059145336122287L;

    public String name;

    public Cat cat;

    public Dog(String name){
        this.name=name;
        this.cat=new Cat("KIT");
    }


    @Override
    public Object clone() {
        try {
            Dog clone = (Dog) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public Object deepClone() throws IOException, ClassNotFoundException {
        //将对象写到流里面:序列化
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);
        //从六里面读出对象:反序列化
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return ois.readObject();
    }
}
