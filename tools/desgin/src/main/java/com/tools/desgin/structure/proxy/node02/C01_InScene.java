package com.tools.desgin.structure.proxy.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/8/21 17:23
 */
public class C01_InScene {
    public static void main(String[]args){
        GetExpress getExpress = new GetExpress();
        getExpress.sureInfo();
        getExpress.signName("张三");

        ExpressAct getExpress1 = new GetExpress();
        ProxyExpress proxyExpress = new ProxyExpress(getExpress1);
        proxyExpress.sureInfo();
        proxyExpress.signName("李四");

        ProxyExpress proxyExpress1 = new ProxyExpress(getExpress);
        proxyExpress1.sureInfo();
        proxyExpress1.signName("小华");
    }
}
