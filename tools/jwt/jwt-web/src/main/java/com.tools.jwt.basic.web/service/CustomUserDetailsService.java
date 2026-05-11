package com.tools.jwt.basic.web.service;

/**
 * @author shenlx
 * @description
 * @date 2026/5/11 10:40
 */

import com.tools.jwt.basic.web.entity.User;
import com.tools.jwt.basic.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
            
            private final UserRepository userRepository;
            
            @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("用户不存在: " + username));
                
                return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .authorities(getAuthorities(user))
                    .accountExpired(false)
                    .accountLocked(!user.getAccountNonLocked())
                    .credentialsExpired(false)
                    .disabled(!user.getEnabled())
                    .build();
            }
            
            private Collection<? extends GrantedAuthority> getAuthorities(User user) {
                Set<GrantedAuthority> authorities = new HashSet<>();
                
                // 添加角色权限
                user.getRoles().forEach(role -> {
                    authorities.add(new SimpleGrantedAuthority(role.getName()));
                    
                    // 添加角色下的具体权限
                    role.getPermissions().forEach(permission -> {
                        authorities.add(new SimpleGrantedAuthority(permission.getCode()));
                    });
                });
                
                return authorities;
            }
        }
