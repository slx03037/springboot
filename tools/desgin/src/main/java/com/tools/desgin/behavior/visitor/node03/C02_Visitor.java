package com.tools.desgin.behavior.visitor.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/10/14 10:32
 */
public class C02_Visitor {

    public static void main1(String[] args) {
        ObjectStructure objectStructure = new ObjectStructure();
        objectStructure.add(new NodeA());
        objectStructure.add(new NodeB());
        Visitor visitorA = new VisitorA();
        objectStructure.doAccept(visitorA);
    }

    public static void main(String[] args) {
        System.out.println(MessageEnum.heat);
    }

    private enum MessageEnum {
        Trigger,//开始操作
        POST_CID,//卡号标识
        sequence,//上位机处理
        Decrypt,//解密
        heat //心跳

    }
}
