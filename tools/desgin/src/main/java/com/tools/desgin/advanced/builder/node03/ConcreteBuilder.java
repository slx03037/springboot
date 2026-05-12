package com.tools.desgin.advanced.builder.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/8/21 14:58
 */
public class ConcreteBuilder extends Builder{
    private final Product product=new Product();
    @Override
    public void builderAct1() {
        product.setAct1("操作1：执行。。。。。。。。。");
    }

    @Override
    public void builderAct2() {
        product.setAct2("操作2：执行。。。。。。。。。");
    }

    @Override
    public Product builderProduct() {
        return product;
    }
}
