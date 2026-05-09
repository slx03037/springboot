package com.mysql.basic.repository;

import com.mysql.basic.entity.UserDO;

import java.util.List;
import java.util.Map;

/**
 * @author shenlx
 * @description
 * @date 2026/3/6 13:58
 */
public interface UserDao {

    int add(UserDO user);
    int update(UserDO user);
    int delete(String username);
    List<Map<String,Object>> queryForList();
    UserDO query(String username);
}
