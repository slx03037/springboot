package com.tools.shiro.web.dao;


import com.tools.shiro.web.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserPermissionMapper {
	
	List<Permission> findByUserName(String userName);
}
