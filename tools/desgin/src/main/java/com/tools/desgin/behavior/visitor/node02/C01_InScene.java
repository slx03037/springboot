package com.tools.desgin.behavior.visitor.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/10/14 10:24
 */
public class C01_InScene {

    public static void main(String []args){
        DataSet dataSet=new DataSet();
        dataSet.addCrowd(new Youth());
        dataSet.addCrowd(new MiddleAge());
        CrowdView against = new Against();
        dataSet.display(against);
        against=new Approve();
        dataSet.display(against);
    }
}
