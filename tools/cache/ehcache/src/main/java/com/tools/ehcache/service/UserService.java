package com.tools.ehcache.service;


import com.tools.ehcache.entity.UserDO;

public interface UserService {

    UserDO update(UserDO user);


    void deleteBySno(String sno);


    UserDO queryBySno(String sno);
}
