package com.tools.document.excel.easypoi.basic.web.model.entity;


import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: my-spring-all-com.xinwen.mybatis.node01.service
 * @description:
 * @author: shenlx
 * @create: 2023-07-10 22:38
 **/

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements BaseEntity {

    private static final long serialVersionUID = 2155315461694084990L;
    /**
     * user id.
     */
    @Excel(name = "ID")
    private Long id;

    /**
     * username.
     */
    @Excel(name = "Name")
    private String userName;

    /**
     * email.
     */
    @Excel(name = "Email")
    private String email;

    /**
     * phoneNumber.
     */
    @Excel(name = "Phone")
    private long phoneNumber;

    /**
     * description.
     */
    @Excel(name = "Description")
    private String description;



}
