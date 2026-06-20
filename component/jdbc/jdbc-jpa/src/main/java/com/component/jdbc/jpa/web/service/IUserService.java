package com.component.jdbc.jpa.web.service;

import com.component.jdbc.jpa.web.entity.UserEntity;
import com.component.jdbc.jpa.web.model.UserQueryBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author shenlx
 * @description
 * @date 2026/6/20 20:52
 */
public interface IUserService extends IBaseService<UserEntity, Long>{
    /**
     * find by page.
     *
     * @param userQueryBean query
     * @param pageRequest   pageRequest
     * @return page
     */
    Page<UserEntity> findPage(UserQueryBean userQueryBean, PageRequest pageRequest);
}
