package com.tools.desgin.behavior.meditaor.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/10/10 14:41
 */
public class ConcreteMediatorA extends Colleague{
    public ConcreteMediatorA(Mediator mediator) {
        super(mediator);
    }

    public void operate(){
        getMediator().notify(this);
    }
}
