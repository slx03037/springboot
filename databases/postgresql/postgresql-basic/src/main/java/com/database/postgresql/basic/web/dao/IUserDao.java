package com.database.postgresql.basic.web.dao;


import com.database.postgresql.basic.web.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author shenlx
 * @description
 * @date 2026/6/24 15:36
 */
@Repository
public interface IUserDao extends IBaseDao<User, Long> {

}
