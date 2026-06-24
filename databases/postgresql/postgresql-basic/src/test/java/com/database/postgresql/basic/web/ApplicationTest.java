package com.database.postgresql.basic.web;

import com.database.postgresql.basic.web.query.UserQueryBean;
import com.database.postgresql.basic.web.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author shenlx
 * @description
 * @date 2026/6/24 16:06
 */
@SpringBootTest
public class ApplicationTest {
    @Autowired
    private IUserService userService;
    /**
     * @return user list
     */
    @Test
    public void queryOne() {
        userService.find(1l);
    }

    /**
     * @return user list
     */
    @Test
    @GetMapping("list")
    public void  list() {
        userService.findPage(UserQueryBean.builder().build(), PageRequest.of(1, 1000));
    }
}
