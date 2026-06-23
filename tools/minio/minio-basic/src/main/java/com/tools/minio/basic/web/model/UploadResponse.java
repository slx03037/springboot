package com.tools.minio.basic.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: my-spring-all-com.xinwen.mybatis.node01.service
 * @description:
 * @author: shenlx
 * @create: 2023-06-23 16:15
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadResponse {
    private String minIoUrl;

    private String nginxUrl;
}
