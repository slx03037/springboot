package com.tools.desgin.behavior.chainofresponsibility.node03;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author shenlx
 * @description 设计模式配置类
 * @date 2024/10/12 16:38
 */
@Configuration
public class PatternConfiguration {

    @Bean
    public HandlerChainManager handlerChainExecute(List<IHandler> handlers){
        HandlerChainManager handlerChainManager = new HandlerChainManager();
        handlerChainManager.setHandlerMap(handlers);

        return handlerChainManager;
    }
}
