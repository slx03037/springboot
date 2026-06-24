package com.tools.excel.easyexcel.async.web.controller;

import com.alibaba.fastjson.JSON;
import com.tools.excel.easyexcel.async.web.common.ExportSo;
import com.tools.excel.easyexcel.async.web.common.enums.BusinessTypeEnum;
import com.tools.excel.easyexcel.async.web.common.enums.TaskDefinition;
import com.tools.excel.easyexcel.async.web.common.enums.TaskStatusEnum;
import com.tools.excel.easyexcel.async.web.entity.ExportTaskEntity;
import com.tools.excel.easyexcel.async.web.service.ExportTaskService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Date;

/**
 * @author shenlx
 * @description
 * @date 2026/6/24 13:36
 */
@Slf4j
@RestController
@RequestMapping("/export")
public class ExportController {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    @Autowired
    private ExportTaskService exportTaskService;

    @PostMapping("/addExportTaskInfo")
    public void addExportTaskInfo(@RequestBody ExportSo exportSo) {
        // 提供新增任务接口给外部调用（dubbo、feign接口等）
        ExportTaskEntity exportTaskEntity = initialExportTaskEntity(exportSo);
        exportTaskService.addExportTask(exportTaskEntity);
        // 导出任务新增成功后，发送mq
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("KEYS", exportTaskEntity.getTaskId());
        SendResult sendResult = rocketMQTemplate.syncSend("TOPIC_EXPORT_TASK", MessageBuilder.createMessage(JSON.toJSONString(exportSo), new MessageHeaders(headerMap)));
        log.info("发送导出任务消息成功 sendResult: {}", sendResult);
    }

    private ExportTaskEntity initialExportTaskEntity(ExportSo exportSo) {
        ExportTaskEntity entity = new ExportTaskEntity();
        entity.setTaskId(UUID.randomUUID().toString().replace("-", ""));
        entity.setBusinessTypeCode(exportSo.getBusinessTypeCode());
        entity.setBusinessTypeName(BusinessTypeEnum.getDescByCode(exportSo.getBusinessTypeCode()));
        entity.setExportParam(JSON.toJSONString(exportSo));
        entity.setTaskStatusCode(TaskStatusEnum.EXPORTING.getCode());
        entity.setTaskStatusName(TaskStatusEnum.EXPORTING.getDesc());
        entity.setFileName(TaskDefinition.getTaskDefinition(exportSo.getBusinessTypeCode()).getFileName());
        entity.setFilePath("");
        entity.setCreateUser("SYS");
        entity.setCreateTime(new Date());
        entity.setUpdateUser(entity.getCreateUser());
        entity.setUpdateTime(entity.getCreateTime());
        return entity;
    }
}