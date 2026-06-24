package com.tools.excel.easyexcel.batch.web.utils;

import com.alibaba.excel.EasyExcel;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author shenlx
 * @description EasyExcel 导出增强工具类 (支持普通/分页模式)
 * @date 2026/6/24 13:53
 */
public class ExcelExporter {
    // ============== 【1. 分页写入 (大数据量首选)】 ==============
    public static <T> void exportByPage(HttpServletResponse response,
                                        String fileName,    // 下载文件名
                                        String sheetName,   // Sheet名称
                                        Class<T> dataModel, // 数据类 (User.class)
                                        int pageSize,       // 每页条数
                                        int totalCount,     // 总条数
                                        PageQuerySupplier<T> pageSupplier) { // 分页查询逻辑

        setupResponse(response, fileName); // 设置响应头

        try (OutputStream out = response.getOutputStream()) {
            //  委托给核心分页工具执行
            //todo PageWriteExcelHelper.writeByPage(out, dataModel, pageSize, totalCount, pageSupplier);
        } catch (Exception e) {
            throw new RuntimeException("导出失败: " + e.getMessage(), e); // 统一异常处理
        }
    }

    // ============== 【2. 普通导出 (小数据量)】 ==============
    public static <T> void exportSimple(HttpServletResponse response,
                                        String fileName,
                                        String sheetName,
                                        Class<T> dataModel,
                                        List<T> dataList) { // 全量数据List

        setupResponse(response, fileName);

        try (OutputStream out = response.getOutputStream()) {
            EasyExcel.write(out, dataModel)
                    .sheet(sheetName)
                    .doWrite(dataList); // 全量写入
        } catch (Exception e) {
            throw new RuntimeException("导出失败: " + e.getMessage(), e);
        }
    }

    // ============== 【私有方法：响应头设置 (复用)】 ==============
    private static void setupResponse(HttpServletResponse response, String fileName) {
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("UTF-8");
            String encodedFileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20"); // 处理空格
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + encodedFileName + ".xlsx");
        } catch (Exception e) {
            throw new RuntimeException("设置响应头失败", e);
        }
    }

    // ============== 【内部接口：分页查询供应商】 ==============
    @FunctionalInterface
    public interface PageQuerySupplier<T> {
        List<T> getPage(int pageNum, int pageSize); // 函数式接口
    }
}
