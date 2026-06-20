package com.component.jdbc.jpa.web.service.impl;

import com.component.jdbc.jpa.web.dao.IBaseDao;
import com.component.jdbc.jpa.web.dao.IRoleDao;
import com.component.jdbc.jpa.web.dao.impl.BaseDoServiceImpl;
import com.component.jdbc.jpa.web.entity.RoleEntity;
import com.component.jdbc.jpa.web.model.RoleQueryBean;
import com.component.jdbc.jpa.web.service.IRoleService;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * @author shenlx
 * @description
 * @date 2026/6/20 20:57
 */
@Service
public class RoleDoServiceImpl extends BaseDoServiceImpl<RoleEntity, Long> implements IRoleService {

    /**
     * roleDao.
     */
    private final IRoleDao roleDao;

    /**
     * init.
     *
     * @param roleDao2 role dao
     */
    public RoleDoServiceImpl(final IRoleDao roleDao2) {
        this.roleDao = roleDao2;
    }

    /**
     * @return base dao
     */
    @Override
    public IBaseDao<RoleEntity, Long> getBaseDao() {
        return this.roleDao;
    }

    /**
     * find page by query.
     *
     * @param roleQueryBean query
     * @param pageRequest   pageRequest
     * @return page
     */
    @Override
    public Page<RoleEntity> findPage(RoleQueryBean roleQueryBean, PageRequest pageRequest) {
        Specification<RoleEntity> specification = Specifications.<RoleEntity>and()
                .like(StringUtils.isNotEmpty(roleQueryBean.getName()), "name",
                        roleQueryBean.getName())
                .like(StringUtils.isNotEmpty(roleQueryBean.getDescription()), "description",
                        roleQueryBean.getDescription())
                .build();
        return this.roleDao.findAll(specification, pageRequest);
    }
}
