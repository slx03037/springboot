package com.tools.shiro.web.dao;


import com.tools.shiro.web.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRoleMapper {
	
	List<Role> findByUserName(String userName);
}
