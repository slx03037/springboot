package com.tools.excel.easyexcel.async.web.handler;

import com.alibaba.fastjson.JSON;
import com.tools.excel.easyexcel.async.web.common.ExportSo;
import com.tools.excel.easyexcel.async.web.template.ExportTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author shenlx
 * @description
 * @date 2026/6/24 13:38
 */
@Slf4j
@Component
@RocketMQMessageListener(
        topic = "TOPIC_EXPORT_TASK",
        consumerGroup = "EXPORT_CONSUME_GROUP")
public class ExportTaskListener implements RocketMQListener<MessageExt> {

    @Autowired
    private ExportTemplate exportTemplate;

    @Override
    public void onMessage(MessageExt messageExt) {
        byte[] body = messageExt.getBody();
        String msg = new String(body);
        log.info("接收到导出任务消息 msg: {}", msg);
        ExportSo exportSo = JSON.parseObject(msg, ExportSo.class);
        // 调用对应业务的导出service
        exportTemplate.exportData(exportSo, messageExt.getKeys());
    }
}
