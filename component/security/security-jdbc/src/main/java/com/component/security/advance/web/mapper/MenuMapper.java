package com.component.security.advance.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.component.security.advance.web.entity.MenuDO;

import java.util.List;


/**
 * @author shenlx
 * @description
 * @date 2026/6/12 21:43
 */
public interface MenuMapper extends BaseMapper<MenuDO> {

    // 查询某一个用户的权限信息
     List<String> findUserMenuById(Long userId) ;
}
