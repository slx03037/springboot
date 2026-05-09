package com.executor.completablefuture.dao;


import com.executor.completablefuture.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author shenlx
 * @description
 * @date 2024/5/17 17:38
 */
public interface MenuRepository extends JpaRepository<Menu, Long> {
}
