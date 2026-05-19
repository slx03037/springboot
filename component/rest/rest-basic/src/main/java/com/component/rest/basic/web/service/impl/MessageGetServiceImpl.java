package com.component.rest.basic.web.service.impl;


import com.component.rest.basic.web.service.MessageGetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @program: my-spring-all-com.xinwen.mybatis.node01.service
 * @description:
 * @author: shenlx
 * @create: 2023-02-10 12:06
 **/
@Service
public class MessageGetServiceImpl implements MessageGetService {
    private static final Logger log= LoggerFactory.getLogger(MessageGetServiceImpl.class);

    private  final String pathUrl="http://127.0.0.1:8082";

    @Override
    public void getString(){
        ResponseEntity<String> responseEntity=new RestTemplate().getForEntity(pathUrl+"/get", String.class);
        log.info("展示结果为：{}",responseEntity);
        String body = responseEntity.getBody();
        log.warn("响应体:{}",body);

    }
}
