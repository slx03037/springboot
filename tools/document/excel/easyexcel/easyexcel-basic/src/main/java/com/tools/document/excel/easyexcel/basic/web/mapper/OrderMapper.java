package com.tools.document.excel.easyexcel.basic.web.mapper;


import com.tools.document.excel.easyexcel.basic.web.entity.OrderBO;

import java.util.List;

/**
 * @program: my-spring-all-com.xinwen.mybatis.node01.service
 * @description:
 * @author: shenlx
 * @create: 2023-06-29 15:38
 **/

public interface OrderMapper {
    List<OrderBO> getOrderlist();
}
