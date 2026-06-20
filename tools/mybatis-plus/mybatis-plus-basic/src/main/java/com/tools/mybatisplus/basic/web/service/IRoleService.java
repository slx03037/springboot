package com.tools.mybatisplus.basic.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tools.mybatisplus.basic.web.entity.RoleDO;
import com.tools.mybatisplus.basic.web.model.RoleQueryBean;

import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2026/6/20 22:06
 */
public interface IRoleService extends IService<RoleDO> {
    List<RoleDO> findList(RoleQueryBean roleQueryBean);
}
