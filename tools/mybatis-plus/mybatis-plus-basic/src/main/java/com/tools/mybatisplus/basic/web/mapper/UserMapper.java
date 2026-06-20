package com.tools.mybatisplus.basic.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tools.mybatisplus.basic.web.entity.UserDO;
import com.tools.mybatisplus.basic.web.model.UserQueryBean;

import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2026/6/20 21:29
 */

public interface UserMapper extends BaseMapper<UserDO> {
    List<UserDO> findList(UserQueryBean userQueryBean);
}
