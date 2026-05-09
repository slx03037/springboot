package com.executor.completablefuture.dao;


import com.executor.completablefuture.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author shenlx
 * @description
 * @date 2024/5/17 17:32
 */
public interface UserRepository extends  JpaRepository<User, Long> {
}
