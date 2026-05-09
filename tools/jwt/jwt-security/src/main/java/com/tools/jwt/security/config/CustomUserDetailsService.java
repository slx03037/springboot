package com.tools.jwt.security.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @author shenlx
 * @description
 * @date 2026/4/22 10:46
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {
    /**
     * 核心方法：根据用户名查询用户
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. 根据用户名查询数据库用户
//        LambdaQueryWrapper<UserPO> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(UserPO::getUsername, username);
//        UserPO userPO = userMapper.selectOne(wrapper);

        // 2. 用户不存在，抛出异常（Spring Security会自动处理）
//        if (userPO == null) {
//            throw new UsernameNotFoundException("用户名不存在");
//        }

        // 3. 封装成 UserDetails 对象返回
        // User 是 Spring Security 提供的 UserDetails 实现类
        return User.builder()
                .username("admin")
                .password("$2a$10$LczbjPW/z17pwg5FyAcsdOf6ZzRwkPzQnRwp5yBshdvldB.vfbWza") // 必须是加密后的密码
                .authorities(Collections.singletonList(() -> "ROLE_USER")) // 权限/角色
                .accountExpired(false) // 账户未过期
                .accountLocked(false)  // 账户未锁定
                .credentialsExpired(false) // 凭证未过期
                //.disabled(true) // 停用
                .build();

    }

}
