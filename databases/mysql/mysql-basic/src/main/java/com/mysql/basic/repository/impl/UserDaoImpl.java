package com.mysql.basic.repository.impl;

import com.mysql.basic.entity.UserDO;
import com.mysql.basic.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;
import java.util.Map;

/**
 * @author shenlx
 * @description
 * @date 2026/3/6 13:58
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public int add(UserDO user) {
//        jdbcTemplate.update("INSERT INTO users (username,password,nick_name,sex,phone)VALUES (?, ?,?,?,?)",
//                user.getUsername(), user.getPassword(),user.getNickName(),user.getSex(),user.getPhone());

        String sql = "insert into sso_user(username,password,nick_name,sex,phone) values(:username,:password,:nickName,:sex,:phone)";
        NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(this.jdbcTemplate.getDataSource());
        return npjt.update(sql, new BeanPropertySqlParameterSource(user));
    }

    @Override
    public int update(UserDO user) {
        String sql = "update sso_user set username = ?,password = ? where user_id = ?";
        Object[] args = { user.getUsername(), user.getPassword(), user.getUserId() };
        int[] argTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
        return jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public int delete(String username) {
        String sql = "delete from user where username = ?";
        Object[] args = { username };
        int[] argTypes = { Types.VARCHAR };
        return jdbcTemplate.update(sql, args, argTypes);
    }

    @Override
    public List<Map<String, Object>> queryForList() {
        String sql = "SELECT user_id,username,password,nick_name,sex,phone FROM sso_user";
        return jdbcTemplate.queryForList(sql);
    }

//    @Autowired
//    private UserMapper userMapper;

    @Override
    public UserDO query(String username) {
        String sql = "SELECT  user_id,username,password,nick_name,sex,phone FROM  sso_user where username = ?";

        //List<UserDO> list = this.jdbcTemplate.query(sql, args, argTypes, userMapper);
//        List<UserDO> list = this.jdbcTemplate.query(sql, args, argTypes,(rs,rowNum)->{
//            UserDO user = new UserDO();
//            user.setUsername(rs.getString("username"));
//            user.setNickName(rs.getString("nick_name"));
//            user.setSex(rs.getString("sex"));
//            user.setUserId(rs.getString("user_id"));
//            user.setPhone(rs.getString("phone"));
//            user.setPassword(rs.getString("password"));
//            return user;
//        });
        Object[] args = { username };
        int[] argTypes = { Types.VARCHAR };
        List<UserDO> list = this.jdbcTemplate.query(sql, args, argTypes,(rs,rowNum)->{
            UserDO user = new UserDO();
            user.setUsername(rs.getString("username"));
            user.setNickName(rs.getString("nick_name"));
            user.setSex(rs.getString("sex"));
            user.setUserId(rs.getString("user_id"));
            user.setPhone(rs.getString("phone"));
            user.setPassword(rs.getString("password"));
            return user;
        });
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
