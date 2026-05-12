package com.tools.desgin.advanced.builder.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/8/21 14:18
 */
public class SoftIml extends BuilderSoft{
    private final MobileSoft mobileSoft=new MobileSoft();

    @Override
    public void earlyWork() {
        System.out.println("软件前期设计。。。");
    }

    @Override
    public void midWork() {
        System.out.println("软件中期架构。。。");
        System.out.println("软件中期开发。。。");
    }

    @Override
    public void lateWork() {
        System.out.println("软件后期上线。。。");
    }

    @Override
    public MobileSoft builderSoft() {
        return mobileSoft;
    }
}
