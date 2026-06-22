package com.tools.jobtime.elasticjob.web.controller;

import com.common.utils.model.Result;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.OneOffJobBootstrap;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author shenlx
 * @description
 * @date 2026/6/22 11:58
 */
@Lazy
@RestController
public class OneOffJobController {
    @Resource
    private OneOffJobBootstrap occurErrorNoticeDingtalkJob;

    @Resource
    private OneOffJobBootstrap occurErrorNoticeWechatJob;

    @Resource
    private OneOffJobBootstrap occurErrorNoticeEmailJob;

    @GetMapping("/execute/occurErrorNoticeDingtalkJob")
    public Result<String> executeOneOffJob() {
        occurErrorNoticeDingtalkJob.execute();
        return Result.success();
    }

    @GetMapping("/execute/occurErrorNoticeWechatJob")
    public Result<String> executeOccurErrorNoticeWechatJob() {
        occurErrorNoticeWechatJob.execute();
        return Result.success();
    }

    @GetMapping("/execute/occurErrorNoticeEmailJob")
    public Result<String> executeOccurErrorNoticeEmailJob() {
        occurErrorNoticeEmailJob.execute();
        return Result.success();
    }
}
