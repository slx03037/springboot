package com.component.bean.interfaces.node01.applicationcontextinitializer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author shenlx
 * @description
 * @date 2025/2/21 16:13
 */
@Slf4j
public class TestApplicationContextInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        /// 在这里进行初始化操作，例如设置环境属性、激活配置文件等
      log.warn("----------ApplicationContextInitializer");
      System.out.println("----------ApplicationContextInitializer");
    }
}
