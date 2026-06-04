package com.component.bean.basic;


import com.component.bean.basic.bean.GetBeanFactoryAware;
import com.component.bean.basic.bean.GetByApplicationContextAware;
import com.component.bean.basic.bean.GetByApplicationListener;
import com.component.bean.basic.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author shenlx
 * @description
 * @date 2025/3/3 17:17
 */
@SpringBootTest
@Slf4j
public class ApplicationTest {
    @Autowired
    private GetBeanFactoryAware beanFactoryAware;

    @Autowired
    private GetByApplicationContextAware applicationContextAware;

    @Autowired
    private GetByApplicationListener applicationListener;

    @Test
    public void testByBeanFactoryAware(){
        UserService userService = beanFactoryAware.get(UserService.class);
        userService.setAge("1");
        log.info(userService.toString());
    }

    @Test
    public void testByApplicationContextAware(){
        UserService userService = applicationContextAware.get(UserService.class);
        userService.setAge("2");
        log.info(userService.toString());
    }

    @Test
    public void testByApplicationListener (){
        UserService userService = applicationListener.get(UserService.class);
        userService.setAge("3");
        log.info(userService.toString());
    }
}
