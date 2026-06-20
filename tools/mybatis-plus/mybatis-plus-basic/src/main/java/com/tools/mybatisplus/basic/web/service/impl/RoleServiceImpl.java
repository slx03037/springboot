package com.tools.mybatisplus.basic.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tools.mybatisplus.basic.web.entity.RoleDO;
import com.tools.mybatisplus.basic.web.mapper.RoleMapper;
import com.tools.mybatisplus.basic.web.model.RoleQueryBean;
import com.tools.mybatisplus.basic.web.service.IRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2026/6/20 22:07
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleDO> implements IRoleService {
    @Override
    public List<RoleDO> findList(RoleQueryBean roleQueryBean) {
        return lambdaQuery().like(StringUtils.isNotEmpty(roleQueryBean.getName()), RoleDO::getName, roleQueryBean.getName())
                .like(StringUtils.isNotEmpty(roleQueryBean.getDescription()), RoleDO::getDescription, roleQueryBean.getDescription())
                .like(StringUtils.isNotEmpty(roleQueryBean.getRoleKey()), RoleDO::getRoleKey, roleQueryBean.getRoleKey())
                .list();
    }
}
