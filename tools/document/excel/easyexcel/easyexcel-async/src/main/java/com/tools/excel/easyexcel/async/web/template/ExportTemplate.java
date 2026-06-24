package com.tools.excel.easyexcel.async.web.template;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSON;
import com.tools.excel.easyexcel.async.web.common.ExportSo;
import com.tools.excel.easyexcel.async.web.common.enums.TaskDefinition;
import com.tools.excel.easyexcel.async.web.common.enums.TaskStatusEnum;
import com.tools.excel.easyexcel.async.web.entity.ExportTaskEntity;
import com.tools.excel.easyexcel.async.web.mapper.ExportTaskMapper;
import com.tools.excel.easyexcel.async.web.service.ExportBaseService;
import com.tools.excel.easyexcel.async.web.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author shenlx
 * @description
 * @date 2026/6/24 13:29
 */
@Slf4j
@Service
public class ExportTemplate {

    @Resource
    private ExportTaskMapper exportTaskMapper;

    public void exportData(ExportSo exportSo, String taskId) {
        String businessTypeCode = exportSo.getBusinessTypeCode();
        TaskDefinition taskDefinition = TaskDefinition.getTaskDefinition(businessTypeCode);
        if (null == taskDefinition) {
            throw new IllegalArgumentException("导出业务类型编码异常！");
        }
        String fileName = taskDefinition.getFileName();
        String filePath = "D:\\home\\excel\\" + fileName + ".xlsx";
        ExportTaskEntity updateExportTaskEntity = new ExportTaskEntity();
        updateExportTaskEntity.setTaskId(taskId);
        updateExportTaskEntity.setFilePath(filePath);
        updateExportTaskEntity.setUpdateTime(new Date());
        updateExportTaskEntity.setTaskStatusCode(TaskStatusEnum.SUCCESS.getCode());
        updateExportTaskEntity.setTaskStatusName(TaskStatusEnum.SUCCESS.getDesc());
        try {
            // 执行导出业务逻辑(查询数据、写入excel)
            Class clazz = parseClazz(taskDefinition.getExportClass());
            ExcelWriter excelWriter = EasyExcelFactory.write(filePath).build();
            WriteSheet writeSheet = EasyExcelFactory.writerSheet(fileName).head(clazz).build();
            String exportService = taskDefinition.getExportService();
            // 不同导出业务，实现ExportBaseService接口即可
            ExportBaseService exportBaseService = (ExportBaseService) SpringUtils.getBean(exportService);
            exportBaseService.export(exportSo, excelWriter, writeSheet);
            excelWriter.finish();
        } catch (Exception e) {
            updateExportTaskEntity.setTaskStatusCode(TaskStatusEnum.FAIL.getCode());
            updateExportTaskEntity.setTaskStatusName(TaskStatusEnum.FAIL.getDesc());
            log.error("导出任务异常 任务ID: {} 导出参数: {} 异常信息: ", taskId, JSON.toJSONString(exportSo), e);
        }
        // 更新导出任务
        exportTaskMapper.updateExportTask(updateExportTaskEntity);
    }

    private Class parseClazz(String exportClass) {
        if (StringUtils.isBlank(exportClass)) {
            throw new IllegalArgumentException("导出实体类全类名不可为空！");
        }
        Class clazz = null;
        try {
            clazz = Class.forName(exportClass);
        } catch (ClassNotFoundException e) {
            log.warn("parseClazz error: ", e);
        }
        if (null == clazz) {
            throw new RuntimeException("clazz解析异常");
        }
        return clazz;
    }
}
