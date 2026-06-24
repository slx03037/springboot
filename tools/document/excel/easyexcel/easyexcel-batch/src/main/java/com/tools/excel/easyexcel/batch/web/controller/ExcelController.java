package com.tools.excel.easyexcel.batch.web.controller;

import com.common.utils.model.Result;
import com.tools.excel.easyexcel.batch.web.model.ExportProgress;
import com.tools.excel.easyexcel.batch.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shenlx
 * @description
 * @date 2026/6/24 13:53
 */
@RestController
public class ExcelController {
    @Autowired
    UserService userService;

    @GetMapping("/export/million")
    public void exportMassiveData(HttpServletResponse response) throws IOException {
        // 1️⃣ 获取总数据量 (用于计算分页)
        int totalUsers = userService.countTotalUsers();

        // 2️⃣ 设置响应头 (固定套路)
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("百万用户数据", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        // 3️⃣ 【核心调用】使用分页工具类导出
//        todo PageWriteExcelHelper.writeByPage(
//                response.getOutputStream(), // 响应输出流
//                User.class,                 // 导出数据模型
//                2000,                      // 每页2000条 (根据业务调整)
//                totalUsers,                 // 总数据量
//                //  Lambda 实现分页查询逻辑 (优雅!)
//                (pageNum, pageSize) -> userService.findByPage(pageNum, pageSize)
//        );
    }

    // 场景1: 导出小数据量 (<1万)
    @GetMapping("/export/users/small")
    public void exportSmallUserList(HttpServletResponse response) {
       //todo List<User> smallList = userService.findRecentUsers(5000); // 查5000条
//        ExcelExporter.exportSimple(
//                response,
//                "最近用户",
//                "用户数据",
//                User.class,
//                smallList
//        );
    }

    // 场景2: 导出大数据量 (10万+)
    @GetMapping("/export/users/large")
    public void exportLargeUserList(HttpServletResponse response) {
        int total = userService.countTotalUsers();
        // todo ExcelExporter.exportByPage(
//                response,
//                "全量用户数据",
//                "用户清单",
//                User.class,
//                3000, // 每批3000条
//                total,
//                (pageNum, pageSize) -> userService.findByPage(pageNum, pageSize) // 你的分页查询
//        );
    }

    // 1. 异步导出接口
    @GetMapping("/export/async")
    public Result<String> triggerAsyncExport() {
        String taskId = "EXPORT_" + System.currentTimeMillis(); // 生成唯一任务ID
        //  提交异步任务 (使用线程池)
       //todo asyncTaskExecutor.execute(() -> doExportTask(taskId));
        return Result.success(String.format("导出任务已提交，请稍后查询进度,%s", taskId));
    }


    // 2. 实际导出任务
    private void doExportTask(String taskId) {
        try {
            // 2.1 保存任务状态 (进行中/0%)
           //todo exportTaskService.save(new ExportTask(taskId, "PROCESSING", 0));

            // 2.2 执行分页导出 (使用我们的ExcelExporter)
            int total = userService.countTotalUsers();
            AtomicInteger exported = new AtomicInteger(0); // 已导出计数器

            // todo ExcelExporter.exportByPage(
               // ..., // response 需要特殊处理 (写文件)
               // ...,
//            (pageNum, pageSize) -> {
//                List<User> page = userService.findByPage(pageNum, pageSize);
//                //  更新进度
//                int currentExported = exported.addAndGet(page.size());
//                int progress = (int) ((currentExported / (double) total) * 100);
//                exportTaskService.updateProgress(taskId, progress);
//                return page;
//            }
 //       );

            // 2.3 任务完成 (100%)
           //todo exportTaskService.updateStatus(taskId, "SUCCESS", 100, filePath); // 存储文件路径
        } catch (Exception e) {
            // 2.4 任务失败
            //todo exportTaskService.updateStatus(taskId, "FAILED", 0, e.getMessage());
        }
    }

    // 3. 进度查询接口
    @GetMapping("/export/progress/{taskId}")
    public Result<ExportProgress> getExportProgress(@PathVariable String taskId) {
        //todo ExportProgress progress = exportTaskService.getProgress(taskId);
        return Result.success();
    }

    // 4. 文件下载接口 (任务成功后)
    @GetMapping("/export/download/{taskId}")
    public void downloadExportFile(@PathVariable String taskId, HttpServletResponse response) {
        //todo String filePath = exportTaskService.getFilePath(taskId);
        // ... 实现文件下载逻辑 ...
    }
}
