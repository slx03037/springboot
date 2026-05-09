CREATE DATABASE
IF NOT EXISTS dev_db DEFAULT CHARACTER
  SET utf8mb4;
  USE dev_db;
CREATE TABLE `sys_app_client` (
                                  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                  `app_key` VARCHAR (64) NOT NULL COMMENT '客户端唯一AppKey',
                                  `app_secret` VARCHAR (128) NOT NULL COMMENT '客户端私密秘钥',
                                  `app_name` VARCHAR (64) NOT NULL COMMENT '客户端名称',
                                  `status` TINYINT NOT NULL DEFAULT '1' COMMENT '状态 0禁用 1正常',
                                  `limit_count` INT DEFAULT '0' COMMENT '每日接口调用限流次数',
                                  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                  PRIMARY KEY (`id`),
                                  UNIQUE KEY `uk_app_key` (`app_key`)
) COMMENT '接口调用方客户端密钥表';