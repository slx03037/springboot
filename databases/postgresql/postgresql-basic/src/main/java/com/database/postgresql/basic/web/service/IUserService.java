package com.database.postgresql.basic.web.service;

import com.database.postgresql.basic.web.entity.User;
import com.database.postgresql.basic.web.query.UserQueryBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author shenlx
 * @description
 * @date 2026/6/24 15:44
 */
public interface IUserService extends IBaseService<User, Long> {

    /**
     * find by page.
     *
     * @param userQueryBean query
     * @param pageRequest   pageRequest
     * @return page
     */
    Page<User> findPage(UserQueryBean userQueryBean, PageRequest pageRequest);

}
