package com.tools.desgin.behavior.chainofresponsibility.node03;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author shenlx
 * @description
 * @date 2024/10/12 17:06
 */

@RestController
@Slf4j
public class TestController {

    @Resource
    private HandlerChainManager handlerChainManager;

    @PostMapping("/send")
    public String duty(@RequestBody String requestBody){
        String response=handlerChainManager.executedHandle("req",requestBody);
        return response;
    }
}
