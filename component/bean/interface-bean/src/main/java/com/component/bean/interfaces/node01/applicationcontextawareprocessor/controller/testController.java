package com.component.bean.interfaces.node01.applicationcontextawareprocessor.controller;

import com.component.bean.interfaces.node01.applicationcontextawareprocessor.ConfigUtil;
import com.component.bean.interfaces.node01.applicationcontextawareprocessor.ExtendResourceLoaderAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenlx
 * @description
 * @date 2025/3/5 15:38
 */
@RestController
public class testController {

    @Autowired
    private ConfigUtil configUtil;

    @GetMapping("/testConfig")
    public void testConfig() {
        String s = configUtil.getPropertiesValue("db.user");
        System.out.println(s);
    }

    @Autowired
    ApplicationContext context;

    @SuppressWarnings("resource")
    @GetMapping("/testResource")
    public void testResource() throws Exception{
        ExtendResourceLoaderAware extendResourceLoaderAware = (ExtendResourceLoaderAware) context.getBean("extendResourceLoaderAware");

        extendResourceLoaderAware.showResourceData();
    }
}
