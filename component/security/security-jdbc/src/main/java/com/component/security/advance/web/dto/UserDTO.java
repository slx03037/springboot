package com.component.security.advance.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2026/6/6 10:19
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDTO implements UserDetails {
    private static final long serialVersionUID = -6871542990409461136L;
    private String username;

    private String password;


    private List<String> perms;

    private List<String> roleList;

    public UserDTO(String username,String password, List<String> perms,List<String> roleList){
        this.username=username;
        this.password=password;
        this.perms=perms;
        this.roleList=roleList;
    }

    /**
     * 将权限关键字换成权限对象进行返回
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> collect = new ArrayList<>();
        for (String perm : perms) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(perm);
            collect.add(simpleGrantedAuthority);
        }
        for(String roleKey : roleList){
            //角色一定要加ROLE_ 才能匹配到,并要注意大小写
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_"+roleKey.toUpperCase());
            collect.add(simpleGrantedAuthority);
        }
//        return perms
//                .stream()
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
