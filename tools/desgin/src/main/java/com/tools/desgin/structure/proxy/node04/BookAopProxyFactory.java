package com.tools.desgin.structure.proxy.node04;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author shenlx
 * @description
 * @date 2024/8/22 16:30
 */
public class BookAopProxyFactory {

    public static BookService createService(){
        //目标类
        final BookService bookService = new BookServiceImpl();

        //切面类
        final BookAspect bookAspect = new BookAspect();

        /**
         * 代理类:将目标类(切入点)和切面类(通知)结合
         */
        BookService proxyInstance = (BookService)Proxy.newProxyInstance(
                BookAopProxyFactory.class.getClassLoader(),
                bookService.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //执行前
                        bookAspect.before();
                        //执行目标类地方法
                        Object obj = method.invoke(bookService, args);
                        //后执行
                        bookAspect.after();
                        return obj;
                    }
                });
        return proxyInstance;
    }
}
