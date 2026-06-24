package com.tools.excel.easyexcel.async.web.common.enums;

/**
 * @author shenlx
 * @description
 * @date 2026/6/24 13:28
 */
public enum TaskStatusEnum {

    EXPORTING("1", "导出中"),
    SUCCESS("2", "成功"),
    FAIL("3", "失败");

    private final String code;
    private final String desc;

    TaskStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
