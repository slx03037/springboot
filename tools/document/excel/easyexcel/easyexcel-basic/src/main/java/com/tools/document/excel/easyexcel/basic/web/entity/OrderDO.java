package com.tools.document.excel.easyexcel.basic.web.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: my-spring-all-com.xinwen.mybatis.node01.service
 * @description:
 * @author: shenlx
 * @create: 2023-06-29 15:35
 **/
@Data
public class OrderDO {
    private String id;

    private String orderId;

    private String address;

    private Date createTime;

    private String productId;

    private String name;

    private String subtitle;

    private String brandName;

    private BigDecimal price;

    private Integer count;
}
