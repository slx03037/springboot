package com.tools.desgin.structure.proxy.node04;

/**
 * @author shenlx
 * @description
 * @date 2024/8/22 16:29
 */
    public class BookServiceImpl implements BookService{
    @Override
    public String getBook() {
        System.out.println("目标方法[getBook]被执行");
        return "高性能Mysql";
    }


}
