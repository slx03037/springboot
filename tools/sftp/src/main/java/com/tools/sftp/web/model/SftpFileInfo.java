package com.tools.sftp.web.model;

import lombok.Data;

/**
 * @author shenlx
 * @description
 * @date 2026/6/19 20:56
 */
@Data
public class SftpFileInfo {
    private String name;
    private long size;          // 字节
    private long modifyTime;    // 毫秒时间戳
    private Boolean isDirectory;
}
