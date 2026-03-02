package com.mybatis.jdbc.service.impl;

import com.mybatis.jdbc.entity.UserDO;
import com.mybatis.jdbc.mapper.UserMapper;
import com.mybatis.jdbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2026/2/24 14:54
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<UserDO> queryAll() {
        List<UserDO> userDOS = userMapper.queryAll();
        return userDOS;
    }
}
