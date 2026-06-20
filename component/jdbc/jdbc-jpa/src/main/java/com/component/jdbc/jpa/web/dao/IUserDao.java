package com.component.jdbc.jpa.web.dao;

import com.component.jdbc.jpa.web.entity.UserEntity;
import org.springframework.stereotype.Repository;

/**
 * @author shenlx
 * @description
 * @date 2026/6/20 20:49
 */
@Repository
public interface IUserDao extends IBaseDao<UserEntity, Long>{
}
