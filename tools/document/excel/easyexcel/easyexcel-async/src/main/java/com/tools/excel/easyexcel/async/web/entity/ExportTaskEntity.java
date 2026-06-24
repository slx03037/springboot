package com.tools.excel.easyexcel.async.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author shenlx
 * @description
 * @date 2026/6/24 13:25
 */
@Data
@TableName("export_task")
public class ExportTaskEntity implements Serializable {

    private static final long serialVersionUID = -5946934679763356534L;

    /**
     * 主键值
     */
    @TableId(value = "id", type = IdType.AUTO)
    private BigInteger id;

    /**
     * 任务ID
     */
    @TableId(value = "task_id")
    private String taskId;

    /**
     * 业务类型编码
     */
    @TableField(value = "business_type_code")
    private String businessTypeCode;

    /**
     * 业务类型名称
     */
    @TableField(value = "business_type_name")
    private String businessTypeName;

    /**
     * 导出参数
     */
    @TableField(value = "export_param")
    private String exportParam;

    /**
     * 导出状态编码
     */
    @TableField(value = "task_status_code")
    private String taskStatusCode;

    /**
     * 导出状态名称
     */
    @TableField(value = "task_status_name")
    private String taskStatusName;

    /**
     * 文件名称
     */
    @TableField(value = "file_name")
    private String fileName;

    /**
     * 文件URL
     */
    @TableField(value = "file_path")
    private String filePath;

    /**
     * 创建人
     */
    @TableField(value = "create_user")
    private String createUser;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新人
     */
    @TableField(value = "update_user")
    private String updateUser;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;
}
