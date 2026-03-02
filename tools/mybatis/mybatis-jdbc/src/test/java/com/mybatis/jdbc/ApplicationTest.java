package com.mybatis.jdbc;

import com.mybatis.jdbc.entity.UserDO;
import com.mybatis.jdbc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2026/2/24 15:00
 */
@SpringBootTest
@Slf4j
public class ApplicationTest {
    @Autowired
    UserService userService;

    @Test
    public void test(){
        List<UserDO> userDOS = userService.queryAll();
        for (UserDO user:userDOS){
            log.warn("用户数据:{}",user);
        }
    }
}
