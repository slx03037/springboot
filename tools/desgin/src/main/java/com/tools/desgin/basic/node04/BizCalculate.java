package com.tools.desgin.basic.node04;

/**
 * @author shenlx
 * @description
 * @date 2024/8/13 15:57
 */
public class BizCalculate extends Calculate{
    private final BaseCalculate baseCalculate = new BaseCalculate() ;
    public int add (int a,int b){
        return this.baseCalculate.add(a,b);
    }
}
