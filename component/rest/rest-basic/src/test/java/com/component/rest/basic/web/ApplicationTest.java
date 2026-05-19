package com.component.rest.basic.web;

import com.component.rest.basic.web.config.prop.RestProperty;
import com.component.rest.basic.web.service.MessageGetService;
import com.component.rest.basic.web.service.MessagePostService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author shenlx
 * @description
 * @date 2026/5/19 11:01
 */
@SpringBootTest
@Slf4j
public class ApplicationTest {

    @Resource
    private MessageGetService messageGetService;
    @Resource
    private MessagePostService messagePostService;
    @Resource
    private RestProperty restProperty;
    @Test
    public void pushString(){
         messageGetService.getString();
    }

    @Test
    public void pushPostString(){
         messagePostService.postString();
    }

    @Test
    public void getPushUrl(){
        String authCenter = restProperty.getAuthCenter();
        log.warn("认证url:{}",authCenter);
    }
}
