package com.tools.excel.easyexcel.async.web.service.impl;

import com.tools.excel.easyexcel.async.web.entity.ExportTaskEntity;
import com.tools.excel.easyexcel.async.web.mapper.ExportTaskMapper;
import com.tools.excel.easyexcel.async.web.service.ExportTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author shenlx
 * @description
 * @date 2026/6/24 13:27
 */
@Slf4j
@Service
public class ExportTaskServiceImpl implements ExportTaskService {

    @Resource
    private ExportTaskMapper exportTaskMapper;

    @Override
    public void addExportTask(ExportTaskEntity entity) {
        exportTaskMapper.addExportTask(entity);
    }

    @Override
    public void updateExportTask(ExportTaskEntity entity) {
        exportTaskMapper.updateExportTask(entity);
    }
}
