package com.tools.excel.easyexcel.async.web.mapper;

import com.tools.excel.easyexcel.async.web.entity.ExportTaskEntity;

/**
 * @author shenlx
 * @description
 * @date 2026/6/24 13:26
 */
public interface ExportTaskMapper {
    /**
     * 新增导出任务
     *
     * @param entity 导出任务实体
     */
    void addExportTask(ExportTaskEntity entity);

    /**
     * 更新导出任务
     *
     * @param entity 导出任务实体
     */
    void updateExportTask(ExportTaskEntity entity);
}
