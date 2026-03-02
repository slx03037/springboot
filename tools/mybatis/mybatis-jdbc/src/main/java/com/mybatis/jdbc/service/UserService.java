package com.mybatis.jdbc.service;

import com.mybatis.jdbc.entity.UserDO;

import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2026/2/24 14:54
 */
public interface UserService {

    List<UserDO> queryAll();
}
