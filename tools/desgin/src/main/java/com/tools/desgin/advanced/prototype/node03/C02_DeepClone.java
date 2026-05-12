package com.tools.desgin.advanced.prototype.node03;

import java.io.IOException;

/**
 * @author shenlx
 * @description
 * @date 2024/8/19 14:23
 */
public class C02_DeepClone {
    public static void main(String[]args) throws IOException, ClassNotFoundException {
        Dog tome = new Dog("tome");
        Dog clone1 = (Dog) tome.clone();
        Dog clone2 = (Dog) tome.clone();

        System.out.println("dog1:"+clone1.cat.hashCode()+"\t;dog2:"+clone2.cat.hashCode());

        Dog clone3 = (Dog) tome.deepClone();
        Dog clone4 = (Dog) tome.deepClone();
        System.out.println("dog3:"+clone3.cat.hashCode()+"\t;dog4:"+clone4.cat.hashCode());
    }
}
