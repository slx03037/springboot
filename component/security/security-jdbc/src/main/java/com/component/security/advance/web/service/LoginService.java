package com.component.security.advance.web.service;

import com.common.utils.model.Result;
import com.component.security.advance.web.bo.UserBO;

/**
 * @author shenlx
 * @description
 * @date 2026/6/6 10:23
 */
public interface LoginService {

    Result<?> login(UserBO userBO);
}
