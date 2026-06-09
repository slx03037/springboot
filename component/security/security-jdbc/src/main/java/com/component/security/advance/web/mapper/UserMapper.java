package com.component.security.advance.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.component.security.advance.web.entity.UserDO;

/**
 * @author shenlx
 * @description
 * @date 2026/6/6 9:57
 */
public interface UserMapper extends BaseMapper<UserDO> {

    UserDO queryUserByName(String userName);
}
