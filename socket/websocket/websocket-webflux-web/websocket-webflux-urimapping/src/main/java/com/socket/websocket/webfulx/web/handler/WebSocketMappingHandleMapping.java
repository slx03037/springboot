package com.socket.websocket.webfulx.web.handler;

import com.socket.websocket.webfulx.web.annotation.WebSocketMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author shenlx
 * @description 实现websocket自动注册映射规则服务
 * @date 2025/3/2 0:15
 */
@Slf4j
public class WebSocketMappingHandleMapping extends SimpleUrlHandlerMapping {

    /**
     *  websocket自定义处理服务集合
     **/
    private final Map<String, WebSocketHandler> handlerMap = new LinkedHashMap<>();

    @Override
    public void initApplicationContext() throws BeansException {
        //使用注解标识的websocket处理服务类集合
        Map<String, Object> beanMap = obtainApplicationContext()
                .getBeansWithAnnotation(WebSocketMapping.class);
        beanMap.values().forEach(bean -> {
            //过滤非websocket服务接口的定义使用
            if (!(bean instanceof WebSocketHandler)) {
                throw new RuntimeException(
                        String.format("Controller [%s] doesn't implement WebSocketHandler interface.",
                                bean.getClass().getName()));
            }
            WebSocketMapping annotation = AnnotationUtils.getAnnotation(
                    bean.getClass(), WebSocketMapping.class);
            //webSocketMapping 映射到管理中
            handlerMap.put(Objects.requireNonNull(annotation).value(),(WebSocketHandler) bean);
        });
        super.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.setUrlMap(handlerMap);
        super.initApplicationContext();
    }

}
