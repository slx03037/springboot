package com.component.security.advance.web;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.component.security.advance.web.entity.UserDO;
import com.component.security.advance.web.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2026/6/9 14:58
 */
@SpringBootTest
@Slf4j
public class SpringTest {
    @Autowired
    private UserMapper userMapper ;

    @Test
    public void findAll() {
        List<UserDO> selectList = userMapper.selectList(new LambdaQueryWrapper<UserDO>());
        selectList.forEach( s -> log.info(JSON.toJSONString(s)) );
    }
}
