package com.tools.desgin.behavior.chainofresponsibility.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/10/12 16:40
 */

@Duty(type="Req",order = 1)
public class SignChainHandler implements IHandler<String,String >{
    @Override
    public String handler(String s) {
        System.out.println("甲方爸爸要求加签");
        return "加签";
    }
}
