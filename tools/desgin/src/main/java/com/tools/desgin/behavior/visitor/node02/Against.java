package com.tools.desgin.behavior.visitor.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/10/14 10:21
 */
public class Against extends CrowdView{
    @Override
    void getYouthView(Youth youth) {
        System.out.println("青年人反对电竞");
    }

    @Override
    void getMiddleAgeView(MiddleAge middleAge) {
        System.out.println("中年人反对电竞");
    }
}
