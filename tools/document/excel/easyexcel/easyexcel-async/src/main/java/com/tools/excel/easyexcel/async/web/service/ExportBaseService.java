package com.tools.excel.easyexcel.async.web.service;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.tools.excel.easyexcel.async.web.common.ExportSo;

/**
 * @author shenlx
 * @description
 * @date 2026/6/24 13:30
 */
public interface ExportBaseService {
    /**
     * 导出基础接口
     *
     * @param exportSo    导出参数
     * @param excelWriter excelWriter对象
     * @param writeSheet  writeSheet对象
     */
    void export(ExportSo exportSo, ExcelWriter excelWriter, WriteSheet writeSheet);
}
