package com.tools.jwt.basic.web.repository;

/**
 * @author shenlx
 * @description
 * @date 2026/5/11 10:41
 */

import com.tools.jwt.basic.web.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);
}
