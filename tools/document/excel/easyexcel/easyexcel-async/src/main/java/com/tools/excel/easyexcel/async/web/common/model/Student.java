package com.tools.excel.easyexcel.async.web.common.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author shenlx
 * @description
 * @date 2026/6/24 13:34
 */
@Data
public class Student implements Serializable {
    private static final long serialVersionUID = -194791133744562176L;
    private Integer id;
    private String stuName;
    private String stuCode;
    private String gender;
    private String cityName;
}
