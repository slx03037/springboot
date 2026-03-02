package com.tools.ehcache;

import com.tools.ehcache.entity.UserDO;
import com.tools.ehcache.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author shenlx
 * @description
 * @date 2026/2/15 21:05
 */
@SpringBootTest
@Slf4j
public class ApplicationTest {

    @Resource
    private UserService userService;
    @Test
    public void getUser(){
        String sno="张三";
        UserDO userDO = userService.queryBySno(sno);
        log.info("userDO:{}",userDO);
    }
}
