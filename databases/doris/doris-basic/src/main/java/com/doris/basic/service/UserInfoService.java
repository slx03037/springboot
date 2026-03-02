package com.doris.basic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.doris.basic.entity.UserInfo;

import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2026/2/10 14:47
 */
public interface UserInfoService extends IService<UserInfo>{

    public int batchInsertUser(List<UserInfo> userList);
}
