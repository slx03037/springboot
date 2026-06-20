package com.component.jdbc.jpa.web.service.impl;

import com.component.jdbc.jpa.web.dao.IBaseDao;
import com.component.jdbc.jpa.web.dao.IUserDao;
import com.component.jdbc.jpa.web.dao.impl.BaseDoServiceImpl;
import com.component.jdbc.jpa.web.entity.UserEntity;
import com.component.jdbc.jpa.web.model.UserQueryBean;
import com.component.jdbc.jpa.web.service.IUserService;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * @author shenlx
 * @description
 * @date 2026/6/20 20:54
 */
@Service
public class UserDoServiceImpl extends BaseDoServiceImpl<UserEntity, Long> implements IUserService {

    /**
     * userDao.
     */
    private final IUserDao userDao;

    /**
     * init.
     *
     * @param userDao2 user dao
     */
    public UserDoServiceImpl(final IUserDao userDao2) {
        this.userDao = userDao2;
    }

    /**
     * @return base dao
     */
    @Override
    public IBaseDao<UserEntity, Long> getBaseDao() {
        return this.userDao;
    }

    /**
     * find by page.
     *
     * @param queryBean   query
     * @param pageRequest pageRequest
     * @return page
     */
    @Override
    public Page<UserEntity> findPage(UserQueryBean queryBean, PageRequest pageRequest) {
        Specification<UserEntity> specification = Specifications.<UserEntity>and()
                .like(StringUtils.isNotEmpty(queryBean.getName()), "user_name", queryBean.getName())
                .like(StringUtils.isNotEmpty(queryBean.getDescription()), "description",
                        queryBean.getDescription())
                .build();
        return this.getBaseDao().findAll(specification, pageRequest);
    }
}
