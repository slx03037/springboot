package com.mysql.basic.mapper;

import com.mysql.basic.entity.UserDO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author shenlx
 * @description
 * @date 2026/3/6 13:50
 */
@Component
public class UserMapper implements RowMapper<UserDO> {


    @Override
    public UserDO mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserDO user = new UserDO();
        user.setUsername(rs.getString("username"));
        user.setNickName(rs.getString("nick_name"));
        user.setSex(rs.getString("sex"));
        user.setUserId(rs.getString("user_id"));
        user.setPhone(rs.getString("phone"));
        user.setPassword(rs.getString("password"));
        return user;
    }
}
