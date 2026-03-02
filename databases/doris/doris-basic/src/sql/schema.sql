-- 注意：Key列（id）是字段第一个字段，符合连续前缀规则
CREATE TABLE IF NOT EXISTS `user_info` (
                                           `id` BIGINT NOT NULL COMMENT '用户ID，主键',
                                           `user_name` VARCHAR(50) NOT NULL COMMENT '用户名（支持emoji）',
    `phone` VARCHAR(20) COMMENT '手机号',
    `email` VARCHAR(100) COMMENT '邮箱',
    `create_time` DATETIME NOT NULL COMMENT '创建时间',
    `update_time` DATETIME NOT NULL  COMMENT '更新时间'
    -- PRIMARY KEY (`id`) -- 表模型嵌入字段末尾
    )
    ENGINE=OLAP
    DUPLICATE KEY (`id`)
    DISTRIBUTED BY HASH(`id`) BUCKETS 8 -- 分桶数：测试8/16，生产32/64
    PROPERTIES (
    "replication_num" = "1" -- 测试1副本，生产3副本
               );


-- 注意：Key列（dt）是第一个字段，符合连续前缀规则；分区列dt非主键
CREATE TABLE IF NOT EXISTS `app_access_log` (
                                                `dt` DATE NOT NULL COMMENT '日志日期，分区列',
                                                `log_id` BIGINT NOT NULL COMMENT '日志ID',
                                                `user_id` BIGINT COMMENT '访问用户ID',
                                                `page_url` VARCHAR(200) COMMENT '访问页面',
    `ip` VARCHAR(30) COMMENT '访问IP',
    `access_time` DATETIME COMMENT '访问时间'
    )
    ENGINE=OLAP
    DUPLICATE KEY (`dt`)
    PARTITION BY RANGE(`dt`) ( -- 按天分区（左闭右开）
    PARTITION `p202602` VALUES LESS THAN ('2026-03-01'),
    PARTITION `p202603` VALUES LESS THAN ('2026-04-01')
    )
    DISTRIBUTED BY HASH(`log_id`) BUCKETS 16
    PROPERTIES (
                   "replication_num" = "1",
                   "dynamic_partition.enable" = "true", -- 开启动态分区
                   "dynamic_partition.time_unit" = "DAY",
                   "dynamic_partition.start" = "-30", -- 保留30天历史
                   "dynamic_partition.end" = "3", -- 预创建3天未来分区
                   "dynamic_partition.prefix" = "p",
                   "dynamic_partition.buckets" = "32"
               );


CREATE TABLE IF NOT EXISTS `page_uv` (
                                         `page_id` INT NOT NULL COMMENT '页面ID',
                                         `dt` DATE NOT NULL COMMENT '日期，分区列',
                                         `user_id_bitmap` BITMAP NOT NULL COMMENT '访问用户ID位图'
    -- 复合主键，连续前缀（1+2字段）
)
    ENGINE=OLAP
    DUPLICATE KEY (`page_id`, `dt`)
    PARTITION BY RANGE(`dt`) (
    PARTITION `p202602` VALUES LESS THAN ('2026-03-01')
    )
    DISTRIBUTED BY HASH(`page_id`) BUCKETS 16
    PROPERTIES (
    "replication_num" = "1"
               );


-- 注意：Key列（dt）是第一个字段，符合连续前缀规则；分区列dt非主键
CREATE TABLE IF NOT EXISTS `app_access_log` (
                                                `dt` DATE NOT NULL COMMENT '日志日期，分区列',
                                                `log_id` BIGINT NOT NULL COMMENT '日志ID',
                                                `user_id` BIGINT COMMENT '访问用户ID',
                                                `page_url` VARCHAR(200) COMMENT '访问页面',
    `ip` VARCHAR(30) COMMENT '访问IP',
    `access_time` DATETIME COMMENT '访问时间'
    )
    ENGINE=OLAP
    DUPLICATE KEY (`dt`)
    PARTITION BY RANGE(`dt`) ( -- 按天分区（左闭右开）
    PARTITION `p202602` VALUES LESS THAN ('2026-03-01'),
    PARTITION `p202603` VALUES LESS THAN ('2026-04-01')
    )
    DISTRIBUTED BY HASH(`log_id`) BUCKETS 16
    PROPERTIES (
                   "replication_num" = "1",
                   "dynamic_partition.enable" = "true", -- 开启动态分区
                   "dynamic_partition.time_unit" = "DAY",
                   "dynamic_partition.start" = "-30", -- 保留30天历史
                   "dynamic_partition.end" = "3", -- 预创建3天未来分区
                   "dynamic_partition.prefix" = "p",
                   "dynamic_partition.buckets" = "32"
               );