package com.component.jdbc.jpa.web.dao;

import com.component.jdbc.jpa.web.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @author shenlx
 * @description
 * @date 2026/6/20 20:48
 */
@NoRepositoryBean
public interface IBaseDao <T extends BaseEntity, I extends Serializable>
        extends JpaRepository<T, I>, JpaSpecificationExecutor<T> {
}
