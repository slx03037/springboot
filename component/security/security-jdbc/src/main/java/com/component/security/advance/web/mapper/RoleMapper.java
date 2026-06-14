package com.component.security.advance.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.component.security.advance.web.entity.RoleDO;

import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2026/6/14 11:56
 */
public interface RoleMapper extends BaseMapper<RoleDO> {
    /**
     * 查询菜单的角色信息
     * @param userId
     * @return
     */
    List<String> findRoleNamesByUserId(Long userId);
}
