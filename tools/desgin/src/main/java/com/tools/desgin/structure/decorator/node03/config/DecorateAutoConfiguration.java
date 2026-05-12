package com.tools.desgin.structure.decorator.node03.config;

import com.xinwen.desgin.structure.decorator.node03.handler.AbstractHandler;
import com.xinwen.desgin.structure.decorator.node03.manager.DecorateManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2024/8/30 14:13
 */
@Configuration
public class DecorateAutoConfiguration {

    @Bean
    public DecorateManager handleDecorate(List<AbstractHandler> handler){
        DecorateManager decorateManager = new DecorateManager();
        decorateManager.setDecorateHandler(handler);
        return decorateManager;
    }
}
