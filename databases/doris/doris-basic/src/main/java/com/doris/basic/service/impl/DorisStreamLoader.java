package com.doris.basic.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2026/2/10 14:22
 */
@Component
public class DorisStreamLoader {
    @Value("${doris.fe-host}")
    private String feHost;

    @Value("${doris.http-port}")
    private int httpPort;

    /**
     * 使用Stream Load导入CSV数据
     */
    public void streamLoadCsv(String database, String table, String csvData) {
        String url = String.format("http://%s:%d/api/%s/%s/_stream_load",
                feHost, httpPort, database, table);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setBasicAuth("root", "NJben@1234"); // Doris账户

        // 设置label（确保幂等性）
        String label = "stream_load_" + System.currentTimeMillis();
        headers.set("label", label);
        headers.set("column_separator", ",");

        HttpEntity<String> request = new HttpEntity<>(csvData, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
                url, request, String.class);

        JSONObject result = JSON.parseObject(response.getBody());
        if (!"Success".equals(result.getString("Status"))) {
            throw new RuntimeException("Stream load failed: " + result);
        }
    }

    /**
     * 导入JSON数据
     */
    public void streamLoadJson(String database, String table, List<?> data) {
        String url = String.format("http://%s:%d/api/%s/%s/_stream_load",
                feHost, httpPort, database, table);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth("root", "NJben@1234");
        headers.set("format", "json");
        headers.set("strip_outer_array", "true");

        String jsonData = JSON.toJSONString(data);
        HttpEntity<String> request = new HttpEntity<>(jsonData, headers);

        // 发送请求...
    }
}
