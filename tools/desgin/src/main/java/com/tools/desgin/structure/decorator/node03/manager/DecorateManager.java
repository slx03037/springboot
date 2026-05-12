package com.tools.desgin.structure.decorator.node03.manager;

import com.xinwen.desgin.structure.decorator.node03.annotaion.Decorate;
import com.xinwen.desgin.structure.decorator.node03.handler.AbstractHandler;
import org.springframework.core.annotation.AnnotationUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shenlx
 * @description
 * @date 2024/8/30 11:37
 */
public class DecorateManager {
    /**
     * 用户存放装饰器类
     */
    private final Map<String, AbstractHandler> decorateHandlerMap=new HashMap<>();

    /**
     * 将具有装饰器的类放在map中
     */
    public void setDecorateHandler(List<AbstractHandler> handlerList){
        for(AbstractHandler handler:handlerList){
            Decorate annotaion= AnnotationUtils.findAnnotation(handler.getClass(),Decorate.class);
            decorateHandlerMap.put(createKey(annotaion.scene(),annotaion.type()),handler);
        }
    }

    public AbstractHandler selectHandler(String scene,String type){
        String key=createKey(scene,type);
        return decorateHandlerMap.get(key);
    }

    private String createKey(String scene,String type){
        return scene + ":" + type;
    }
}
