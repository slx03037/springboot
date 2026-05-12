package com.tools.desgin.structure.composite.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/9/3 15:58
 */
public class C02_Security_Model {

    public static void main(String[]args){
        Composite root=new Composite("服装");
        Composite composite1=new Composite("男装");

        Leaf manCoat=new Leaf("上衣");
        Leaf manBottom=new Leaf("下依");

        composite1.addChild(manCoat);
        composite1.addChild(manBottom);

        Composite composite2=new Composite("女装");
        Leaf leaf1=new Leaf("鞋子");
        Leaf leaf2=new Leaf("帽子");

        root.addChild(leaf1);
        root.addChild(leaf2);
        root.addChild(composite1);
        root.addChild(composite2);
        root.printStruct("");
    }
}
