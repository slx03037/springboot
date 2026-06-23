package com.tools.minio.single.web.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @author shenlx
 * @description
 * @date 2026/1/31 20:37
 */
@Data
@AllArgsConstructor
public class FileInfo {
    private String fileName;
    private String url;
    private long size;
    private Date lastModified;
}
