CREATE DATABASE bs
DEFAULT CHARACTER SET utf8mb4
DEFAULT COLLATE utf8mb4_general_ci;

use bs;

CREATE TABLE export_task (
                             id BIGINT UNSIGNED auto_increment COMMENT '主键值' PRIMARY KEY,
                             task_id VARCHAR (64) NULL COMMENT 'MRP任务Id',
                             business_type_code VARCHAR (10) DEFAULT '' NOT NULL COMMENT '业务类型编码',
                             business_type_name VARCHAR (20) DEFAULT '' NOT NULL COMMENT '业务类型名称',
                             export_param TEXT  NOT NULL COMMENT '导出参数',
                             task_status_code VARCHAR (5) DEFAULT '' NOT NULL COMMENT '导出状态编码',
                             task_status_name VARCHAR (10) DEFAULT '' NOT NULL COMMENT '导出状态名称',
                             file_name VARCHAR (255) DEFAULT '' NOT NULL COMMENT '文件名称',
                             file_path VARCHAR (500) DEFAULT '' NOT NULL COMMENT '文件URL',
                             create_user VARCHAR (64) DEFAULT '' NOT NULL COMMENT '创建人',
                             create_time DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL COMMENT '创建时间',
                             update_user VARCHAR (64) DEFAULT '' NOT NULL COMMENT '更新人',
                             update_time DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL COMMENT '修改时间'
) COMMENT '导出任务表';
CREATE INDEX idx_task_id ON export_task (task_id);