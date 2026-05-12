package com.tools.desgin.structure.proxy.node04;

/**
 * @author shenlx
 * @description
 * @date 2024/8/22 16:38
 */
public class C03_JdkProxy {
    public static  void main(String[]args){
        BookService service = BookAopProxyFactory.createService();
        System.out.println(service.getBook());
    }
}
