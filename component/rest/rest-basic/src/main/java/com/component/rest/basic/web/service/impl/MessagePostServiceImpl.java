package com.component.rest.basic.web.service.impl;


import com.component.rest.basic.web.domain.User;
import com.component.rest.basic.web.service.MessagePostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;


/**
 * @program: my-spring-all-com.xinwen.mybatis.node01.service
 * @description:
 * @author: shenlx
 * @create: 2023-02-10 13:06
 **/
@Service
public class MessagePostServiceImpl implements MessagePostService {
    private static final Logger log= LoggerFactory.getLogger(MessagePostServiceImpl.class);

    @Resource
    private RestTemplate restTemplate;

    private  final String pathUrl="http://127.0.0.1:8082";

    @Override
    public void postString() {
        User user = new User();
        user.setAge(8);
        user.setName("张三");
        user.setSex((byte) 0);
        ResponseEntity<String> userResponseEntity = restTemplate.postForEntity(pathUrl + "/post", user, String.class);

        //Result<User> userCommonResult= JacksonUtil.json2Bean(userResponseEntity.getBody(), new TypeReference<Result<User>>() {});

        log.info("请求体：{}",userResponseEntity);
        //CommonResult<User> userCommonResult=userResponseEntity.getBody();
        //CommonResult<User> body = JSON.parseObject(body1,CommonResult.class);
        //log.info("请求body：{}",userCommonResult);

        //log.info("data值：{}", userCommonResult.getData());
        //log.info("User实体类：{}",user1);

       // return userCommonResult.getData().getName();
    }
}
