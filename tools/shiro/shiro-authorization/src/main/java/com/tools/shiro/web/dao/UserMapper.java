package com.tools.shiro.web.dao;


import com.tools.shiro.web.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
	User findByUserName(@Param("username") String username);
}
