package com.tools.mybatisplus.basic.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tools.mybatisplus.basic.web.entity.UserDO;
import com.tools.mybatisplus.basic.web.mapper.UserMapper;
import com.tools.mybatisplus.basic.web.model.UserQueryBean;
import com.tools.mybatisplus.basic.web.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2026/6/20 22:03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDO> findList(UserQueryBean userQueryBean) {
        return userMapper.findList(userQueryBean);
    }
}
