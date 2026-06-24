package com.database.postgresql.basic.web.query;

import lombok.Builder;
import lombok.Data;

/**
 * @author shenlx
 * @description
 * @date 2026/6/24 15:47
 */
@Data
@Builder
public class UserQueryBean {

    /**
     * contains name pattern.
     */
    private String name;

    /**
     * contains desc pattern.
     */
    private String description;

}