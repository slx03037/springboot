package com.component.bean.interfaces.node01.instantiationawarebeanpostprocessor.d1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenlx
 * @description
 * @date 2025/2/26 14:38
 */
@RestController
@RequestMapping("/example")
@Slf4j
public class ExampleController {
    private String creator="gaox";
    private ExampleService exampleService;
    @Autowired
    public void setExampleService(ExampleService exampleService) {
        this.exampleService = exampleService;
        log.info("----ExampleController内的exampleService属性被注入");
    }
    public void setCreator(String creator) {
        this.creator = creator;
        log.info("----ExampleController内的creator属性被注入");
    }
    public String getCreator() {
        return creator;
    }
    public ExampleController() {
        log.info("----ExampleController无参构造方法被执行");
    }
}
