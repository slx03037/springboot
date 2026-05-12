package com.tools.desgin.structure.proxy.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/8/21 17:20
 */
public class GetExpress implements ExpressAct{
    @Override
    public void sureInfo() {
        System.out.println("请求确认你的个人信息");
    }

    @Override
    public void signName(String name) {
        System.out.println("你的名字："+name);
    }
}
