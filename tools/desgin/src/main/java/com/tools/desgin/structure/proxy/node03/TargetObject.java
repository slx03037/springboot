package com.tools.desgin.structure.proxy.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/8/22 16:25
 */
public class TargetObject extends AbstractObject{
    @Override
    public void operation() {
        System.out.println("Target Method Run...");
    }
}
