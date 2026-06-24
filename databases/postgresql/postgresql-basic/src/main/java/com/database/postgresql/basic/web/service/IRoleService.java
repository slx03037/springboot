package com.database.postgresql.basic.web.service;

import com.database.postgresql.basic.web.entity.Role;
import com.database.postgresql.basic.web.query.RoleQueryBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author shenlx
 * @description
 * @date 2026/6/24 15:44
 */
public interface IRoleService extends IBaseService<Role, Long> {

    /**
     * find page by query.
     *
     * @param roleQueryBean query
     * @param pageRequest   pageRequest
     * @return page
     */
    Page<Role> findPage(RoleQueryBean roleQueryBean, PageRequest pageRequest);

}
