package com.tools.jobtime.quartz.cluster.web.model;

import lombok.Data;

import java.util.Date;

/**
 * @author shenlx
 * @description
 * @date 2026/6/22 11:23
 */
@Data
public class JobDetails {
    private String cronExpression;
    private String jobClassName;
    private String triggerGroupName;
    private String triggerName;
    private String jobGroupName;
    private String jobName;
    private Date nextFireTime;
    private Date previousFireTime;
    private Date startTime;
    private String timeZone;
    private String status;
}
