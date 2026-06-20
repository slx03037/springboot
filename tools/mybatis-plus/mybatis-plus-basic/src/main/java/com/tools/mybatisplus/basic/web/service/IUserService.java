package com.tools.mybatisplus.basic.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tools.mybatisplus.basic.web.entity.UserDO;
import com.tools.mybatisplus.basic.web.model.UserQueryBean;

import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2026/6/20 22:03
 */
public interface IUserService extends IService<UserDO> {
    List<UserDO> findList(UserQueryBean userQueryBean);
}
