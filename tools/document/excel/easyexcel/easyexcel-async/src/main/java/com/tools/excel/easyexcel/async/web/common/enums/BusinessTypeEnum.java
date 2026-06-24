package com.tools.excel.easyexcel.async.web.common.enums;

/**
 * @author shenlx
 * @description
 * @date 2026/6/24 13:28
 */
public enum BusinessTypeEnum {

    EXPORTING("1", "学生信息导出"),
    COMPLETED("2", "教师信息导出");

    private final String code;
    private final String desc;

    BusinessTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getDescByCode(String code) {
        for (BusinessTypeEnum item : BusinessTypeEnum.values()) {
            if (code.equals(item.getCode())) {
                return item.getDesc();
            }
        }
        return "";
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}