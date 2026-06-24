package com.tools.excel.easyexcel.async.web.common.enums;

import java.util.stream.Stream;

/**
 * @author shenlx
 * @description
 * @date 2026/6/24 13:28
 */
public enum TaskDefinition {
    STUDENT_INFO_EXPORT("1", "studentExportService", "学生信息导出", "com.easyexcel.demo.entity.Student");

    private final String businessTypeCode;
    private final String exportService;
    private final String fileName;
    private final String exportClass;

    TaskDefinition(String businessTypeCode, String exportService, String fileName, String exportClass) {
        this.businessTypeCode = businessTypeCode;
        this.exportService = exportService;
        this.fileName = fileName;
        this.exportClass = exportClass;
    }

    public static TaskDefinition getTaskDefinition(String businessTypeCode) {
        return Stream.of(values())
                .filter(item -> businessTypeCode.equals(item.getBusinessTypeCode()))
                .findFirst()
                .orElse(null);
    }

    public String getBusinessTypeCode() {
        return businessTypeCode;
    }

    public String getExportService() {
        return exportService;
    }

    public String getFileName() {
        return fileName;
    }

    public String getExportClass() {
        return exportClass;
    }
}
