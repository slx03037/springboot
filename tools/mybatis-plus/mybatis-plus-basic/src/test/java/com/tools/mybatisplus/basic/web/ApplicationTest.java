package com.tools.mybatisplus.basic.web;

import com.tools.mybatisplus.basic.web.entity.RoleDO;
import com.tools.mybatisplus.basic.web.entity.UserDO;
import com.tools.mybatisplus.basic.web.model.RoleQueryBean;
import com.tools.mybatisplus.basic.web.model.UserQueryBean;
import com.tools.mybatisplus.basic.web.service.IRoleService;
import com.tools.mybatisplus.basic.web.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2026/6/20 22:09
 */
@SpringBootTest
@Slf4j
public class ApplicationTest {

    @Autowired
    private IUserService userService;

    @Test
    public void add() {

        UserDO userEntity=new UserDO();
        userEntity.setUserName("dev");
        userEntity.setEmail("123456@gmail.com");
        userEntity.setPhoneNumber(1234567891l);
        userEntity.setPassword("123456");
        if (userEntity.getId() == null) {
            userEntity.setCreateTime(LocalDateTime.now());
        }
        userEntity.setUpdateTime(LocalDateTime.now());
        userService.save(userEntity);
        UserDO user = userService.getById(userEntity.getId());
        log.warn("用户信息：{}",user);
    }


    @Test
    public void edit() {
        UserDO user = userService.getById(2l);
        log.warn("用户信息：{}",user);
    }

    @Test
    public void list() {
        UserQueryBean userQueryBean=new UserQueryBean();
        List<UserDO> list = userService.findList(userQueryBean);
        log.warn("用户集合信息：{}",list);
    }

    @Autowired
    private IRoleService roleService;

    @Test
    public void listRole() {
        RoleQueryBean roleQueryBean=new RoleQueryBean();
        List<RoleDO> list = roleService.findList(roleQueryBean);
        log.warn("权限集合信息：{}",list);
    }
}
