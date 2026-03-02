package com.jdbc.basic.mapper;

import com.jdbc.basic.entity.Category;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2026/2/24 21:27
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {
    List<Category> findAllByNameContaining(String name);

    List<CategoryProjection> findProjectedByNameContaining(String name);

    @Query("SELECT * FROM category WHERE name = :name")
    List<Category> findWithDeclaredQuery(String name);
}
