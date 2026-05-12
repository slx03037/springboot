package com.tools.desgin.behavior.chainofresponsibility.node03;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author shenlx
 * @description
 * @date 2024/10/12 16:30
 */
public class HandlerChainManager {

    /**
     * 存放责任链路伤的具体处理类
     * K 具体业务场景名称
     * V 具体业务场景下的责任链集合
     */
    private Map<String, List<IHandler>> handlerMap;

    /**
     * 存放系统中责任链具体处理类
     * @param handleList
     */
    public  void setHandlerMap(List<IHandler> handleList){
        handlerMap=handleList
                .stream()
                .sorted(Comparator.comparingInt(h-> AnnotationUtils.findAnnotation(h.getClass(), Duty.class).order()))
                .collect(Collectors.groupingBy(handler->AnnotationUtils.findAnnotation(handler.getClass(),Duty.class).type()));
    }

    /**
     * 指向具体业务场景中的责任链集合
     * @param type  对应@Duty注解中的type，可以定义为具体业务场景
     * @param t  被执行的参数
     * @return
     * @param <T>
     * @param <R>
     */
    public <T,R> R executedHandle(String type,T t){
        List<IHandler> handlers=handlerMap.get(type);

        R r=null;

        if(!CollectionUtils.isEmpty(handlers)){
            for(IHandler<T,R> handler:handlers){
                r=handler.handler(t);
            }
        }
        return r;
    }


}
