package com.executor.completablefuture.dao;


import com.executor.completablefuture.entity.Dept;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author shenlx
 * @description
 * @date 2024/5/17 17:38
 */
public interface DeptRepository extends JpaRepository<Dept, Long> {
}
