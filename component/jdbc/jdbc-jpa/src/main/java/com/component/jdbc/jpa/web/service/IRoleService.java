package com.component.jdbc.jpa.web.service;

import com.component.jdbc.jpa.web.entity.RoleEntity;
import com.component.jdbc.jpa.web.model.RoleQueryBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author shenlx
 * @description
 * @date 2026/6/20 20:56
 */
public interface IRoleService extends IBaseService<RoleEntity, Long>{

    /**
     * find page by query.
     *
     * @param roleQueryBean query
     * @param pageRequest   pageRequest
     * @return page
     */
    Page<RoleEntity> findPage(RoleQueryBean roleQueryBean, PageRequest pageRequest);
}
