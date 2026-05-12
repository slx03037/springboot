package com.tools.desgin.behavior.meditaor.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/10/10 14:40
 */
public class Colleague {
    private Mediator mediator;

    public Mediator getMediator() {
        return mediator;
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }
}
