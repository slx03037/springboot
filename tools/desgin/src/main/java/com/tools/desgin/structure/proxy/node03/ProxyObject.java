package com.tools.desgin.structure.proxy.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/8/22 16:25
 */
public class ProxyObject extends AbstractObject{
    TargetObject targetObject=new TargetObject();
    @Override
    public void operation() {
        System.out.println("Method Before");
        targetObject.operation();
        System.out.println("Method After");
    }
}
