package com.component.security.advance.web.service.impl;

import com.component.security.advance.web.dto.UserDTO;
import com.component.security.advance.web.entity.UserDO;
import com.component.security.advance.web.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author shenlx
 * @description
 * @date 2026/6/6 9:44
 */
@Service
@Slf4j
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDO user = userMapper.queryUserByName(username);
        if (null == user) {
            log.info("登录用户：{} 不存在.", username);
            throw new UsernameNotFoundException("登录用户不存在");
        } else if ("2".equals(user.getStatus())) {
            log.info("登录用户：{} 已被删除.", username);
            throw new AccountExpiredException("用户已被删除");
        } else if ("1".equals(user.getStatus())) {
            log.info("登录用户：{} 已被停用.", username);
            throw new DisabledException("用户已停用");
        }
        return createUserDetails(user);
    }

    private UserDetails createUserDetails(UserDO user){
        UserDTO userDTO = new UserDTO(user.getUsername(), user.getPassword());
        log.info("UserDetails的数据为:{}",userDTO);
        return userDTO;
    }
}
