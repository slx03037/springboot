package com.mybatis.jdbc.mapper;

import com.mybatis.jdbc.entity.UserDO;

import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2026/2/24 14:49
 */
public interface UserMapper {
    List<UserDO> queryAll();
}
