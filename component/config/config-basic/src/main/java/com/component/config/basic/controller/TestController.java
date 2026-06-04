package com.component.config.basic.controller;


import com.component.config.basic.bean.PrefixPropertiesArrayBeans;
import com.component.config.basic.bean.PropertiesGetBean;
import com.component.config.basic.bean.TestConfigBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: my-spring-all-com.xinwen.mybatis.node01.service
 * @description:
 * @author: shenlx
 * @create: 2023-03-10 11:27
 **/
@RestController
public class TestController {
    @Resource
    private PropertiesGetBean propertiesGetBean;

    @Resource
    private PrefixPropertiesArrayBeans prefixPropertiesArrayBeans;

    @Resource
    private TestConfigBean testConfigBean;

    @GetMapping("/get")
    public String get(){
        return String.format("端口号:%s",propertiesGetBean.getPort());
    }

    @GetMapping("/getObj")
    public String getObj(){
        return String.format("obj:%s",prefixPropertiesArrayBeans.toString());
    }

    @GetMapping("/getTest")
    public String getTest(){
        return String.format("指定文件路径:%s",testConfigBean.toString());
    }
}
