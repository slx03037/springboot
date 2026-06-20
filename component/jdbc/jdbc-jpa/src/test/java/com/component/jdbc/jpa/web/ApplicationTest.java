package com.component.jdbc.jpa.web;

import com.component.jdbc.jpa.web.entity.RoleEntity;
import com.component.jdbc.jpa.web.entity.UserEntity;
import com.component.jdbc.jpa.web.model.UserQueryBean;
import com.component.jdbc.jpa.web.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author shenlx
 * @description
 * @date 2026/6/20 20:59
 */
@SpringBootTest
@Slf4j
public class ApplicationTest {

    @Autowired
    private IUserService userService;

    @Test
    public void add() {
        UserEntity userEntity=new UserEntity();
        userEntity.setUserName("admin");
        userEntity.setEmail("123456@gmail.com");
        userEntity.setPhoneNumber(1234567891l);
        userEntity.setPassword("123456");
        Set<RoleEntity> list=new HashSet<>();
        RoleEntity roleEntity=new RoleEntity();
        roleEntity.setRoleKey("admin");
        roleEntity.setName("admin");
        roleEntity.setId(1l);
        list.add(roleEntity);
        userEntity.setRoles(list);
        if (userEntity.getId()==null || !userService.exists(userEntity.getId())) {
            userEntity.setCreateTime(LocalDateTime.now());
            userEntity.setUpdateTime(LocalDateTime.now());
            userService.save(userEntity);
        } else {
            userEntity.setUpdateTime(LocalDateTime.now());
            userService.update(userEntity);
        }
        UserEntity user = userService.find(userEntity.getId());
        log.warn("查询得用户信息:{}",user);
    }


    @Test
    public void edit( ){
        UserEntity userEntity = userService.find(1L);
        log.warn("用户信息:{}",userEntity);
    }

    @Test
    public void list() {
        UserQueryBean queryBean=new UserQueryBean();
        userService.findPage(queryBean, PageRequest.of(1, 10));
    }
}
