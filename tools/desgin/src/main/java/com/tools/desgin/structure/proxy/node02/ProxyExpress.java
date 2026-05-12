package com.tools.desgin.structure.proxy.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/8/21 17:22
 */
public class ProxyExpress implements ExpressAct{
    private ExpressAct expressAct=null;

    public ProxyExpress(ExpressAct expressAct) {
        this.expressAct = expressAct;
    }

    @Override
    public void sureInfo() {
        this.expressAct.sureInfo();
    }

    @Override
    public void signName(String name) {
        this.expressAct.signName(name);
    }
}
