package com.database.elasticsearch.basic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shenlx
 * @version 1.0.0
 * @date 2023-11-07 22:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String sku;
    private String title;
    private Double price;
}
