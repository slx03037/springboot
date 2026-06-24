package com.tools.excel.easyexcel.batch.web.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.tools.excel.easyexcel.batch.web.model.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2026/6/24 13:55
 */
@Service
public class UserService {
    public int countTotalUsers() {
        return 100000;
    }

    public static int calculateOptimalPageSize(Class<?> clazz) {
        // 1. 估算单条数据大小 (字节) - 根据业务模型调整逻辑
        long approxBytesPerRow = 500; // 保守估计500字节/行

        // 2. 获取当前JVM可用内存 (转成字节)
        long freeMemoryBytes = Runtime.getRuntime().freeMemory();

        // 3. 【安全策略】仅使用一部分可用内存 (例如 40%)
        long safeMemoryToUse = (long) (freeMemoryBytes * 0.4);

        // 4. 计算建议分页条数
        int suggestedPageSize = (int) (safeMemoryToUse / approxBytesPerRow);

        // 5. 设置合理范围 (防止太大或太小)
        return Math.max(1000, Math.min(suggestedPageSize, 10000)); // 限制在1000~10000条/页
    }

    // 使用动态分页
    //int dynamicPageSize = calculateOptimalPageSize(User.class);
    //ExcelExporter.exportByPage(..., dynamicPageSize, ...);

    public void manySheet(OutputStream outputStream){
        try (ExcelWriter excelWriter = EasyExcel.write(outputStream).build()) {
            List<String> sheetNames = Arrays.asList("用户信息", "订单记录", "操作日志");
            for (int i = 0; i < sheetNames.size(); i++) {
                WriteSheet sheet = EasyExcel.writerSheet(i, sheetNames.get(i)).head(User.class).build(); // 根据Sheet设置不同head
                // 对该Sheet进行分页写入 (复用前面的分页逻辑)...
                //todo PageWriteExcelHelper.writeForSheet(excelWriter, sheet);
            }
        } // try-with-resources自动关闭excelWriter
    }

    public void templateFile(HttpServletResponse response) {
        // 1. 提前准备好带样式的 template.xlsx 放在资源目录
// 2. 模板导出代码
        String templateFile = "/templates/complex-report-template.xlsx";
        try (InputStream templateStream = getClass().getResourceAsStream(templateFile);
             ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream())
                     .withTemplate(templateStream)
                     .build()) {

            WriteSheet writeSheet = EasyExcel.writerSheet().build();
            // 填充单个数据
           //todo excelWriter.fill(new TemplateData(...),writeSheet);
            // 填充列表数据 (支持分页填充!)
           //todo excelWriter.fill(new FillWrapper("dataList", pageData), writeSheet); // 'dataList' 是模板里的变量名
            // ... 填充更多数据 ...
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}