package com.tools.document.excel.easyexcel.basic.web.mapper;


import com.tools.document.excel.easyexcel.basic.web.entity.UserDO;

import java.util.List;

/**
 * @program: my-spring-all-com.xinwen.mybatis.node01.service
 * @description:
 * @author: shenlx
 * @create: 2023-06-29 14:31
 **/

public interface UserMapper {
    List<UserDO>  excelUserList();
}
