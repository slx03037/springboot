package com.component.security.advance.web.service.impl;

import com.common.utils.model.Result;
import com.component.security.advance.web.bo.UserBO;
import com.component.security.advance.web.cache.UserCache;
import com.component.security.advance.web.dto.UserDTO;
import com.component.security.advance.web.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author shenlx
 * @description
 * @date 2026/6/6 10:23
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;



    @Override
    public Result<?> login(UserBO userBO) {
        //验证User账号和密码是否存在和正确
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userBO.getUsername(), userBO.getPassword());
        //封装未认证令牌
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //生成token 用uuid替代
        String token = UUID.randomUUID().toString();
        
        //转换UserDTO信息
        UserDTO details = (UserDTO)authenticate.getPrincipal();
        UserCache.userMap.put(token,details);
        log.info("token:{},用户信息:{}",token,details);
        return Result.success(token);
    }
}
