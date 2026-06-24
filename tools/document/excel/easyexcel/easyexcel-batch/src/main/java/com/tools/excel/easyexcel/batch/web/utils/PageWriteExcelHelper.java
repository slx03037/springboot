package com.tools.excel.easyexcel.batch.web.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.io.OutputStream;
import java.util.List;

/**
 * @author shenlx
 * @description 分页写入Excel工具
 * @date 2026/6/24 13:52
 */
public class PageWriteExcelHelper<T> {
    //  关键接口：定义如何分页获取数据 (由调用方实现)
    public interface PageQuerySupplier<T> {
        List<T> getPage(int pageNum, int pageSize); // 第几页? 每页几条?
    }

    /**
     * 执行分页写入
     * @param outputStream  输出流 (响应OutputStream)
     * @param head          数据模型Class (如 User.class)
     * @param pageSize      【重要】每批次处理条数 (建议 1000~5000)
     * @param totalCount    总数据量 (用于计算总页数)
     * @param supplier      分页数据提供器 (你的业务查询逻辑)
     */
    public static <T> void writeByPage(OutputStream outputStream,
                                       Class<T> head,
                                       int pageSize,
                                       int totalCount,
                                       PageQuerySupplier<T> supplier) {
        //  1. 初始化 ExcelWriter (EasyExcel 核心写入器)
        ExcelWriter excelWriter = EasyExcel.write(outputStream, head).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("Sheet1").build(); // 默认Sheet

        try {
            //  2. 计算总页数 (小心除0)
            int totalPage = totalCount > 0 ? (int) Math.ceil((double) totalCount / pageSize) : 1;

            //  3. 分页循环：查询 -> 写入 -> 释放
            for (int pageNum = 1; pageNum <= totalPage; pageNum++) {
                //  3.1 获取当前页数据 (你的分页查询)
                List<T> pageData = supplier.getPage(pageNum, pageSize);

                // ✍️ 3.2 写入当前页到 Excel
                excelWriter.write(pageData, writeSheet);

                // ️ 3.3 【关键】立即清空释放当前页内存！
                pageData.clear();
            }
        } finally {
            //  4. 【务必关闭】释放资源 (防止内存泄漏)
            if (excelWriter != null) {
                excelWriter.finish(); // 重要！！！
            }
        }
    }
}
