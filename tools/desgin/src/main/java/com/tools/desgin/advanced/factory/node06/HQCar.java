package com.tools.desgin.advanced.factory.node06;

/**
 * @author shenlx
 * @description
 * @date 2024/8/15 17:27
 */
public class HQCar extends CarProduct{
    @Override
    void material() {
        System.out.println(super.name+"材料");
    }

    @Override
    void origin() {
        System.out.println(super.date+":"+super.name+"在中国北京生产");
    }
}
