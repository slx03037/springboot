package com.doris.basic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author shenlx
 * @description
 * @date 2026/2/10 14:28
 */

@Service
public class VectorizedQueryService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 开启向量化查询
     */
    public List<Map<String, Object>> vectorizedQuery() {
        // 设置会话变量启用向量化执行
        jdbcTemplate.execute("SET enable_vectorized_engine = true");
        jdbcTemplate.execute("SET batch_size = 1024");

        String sql = "SELECT /*+ SET_VAR(query_timeout=300) */ " +
                "user_id, SUM(cnt) FROM large_table GROUP BY user_id";

        return jdbcTemplate.queryForList(sql);
    }
}
