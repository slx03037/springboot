package com.tools.desgin.behavior.meditaor.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/10/10 14:41
 */
public class ConcreteMediatorB extends Colleague{

    public ConcreteMediatorB(Mediator mediator) {
        super(mediator);
    }

    public void operate(){
        getMediator().notify(this);
    }
}
