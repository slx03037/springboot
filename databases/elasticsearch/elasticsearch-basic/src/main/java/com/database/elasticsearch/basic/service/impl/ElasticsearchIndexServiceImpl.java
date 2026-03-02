package com.database.elasticsearch.basic.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.cat.IndicesResponse;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import co.elastic.clients.elasticsearch.indices.IndexState;
import com.database.elasticsearch.basic.service.ElasticsearchIndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author shenlx
 * @description
 * @date 2026/2/15 21:42
 */
@Service
@Slf4j
public class ElasticsearchIndexServiceImpl implements ElasticsearchIndexService {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @Override
    public String getIndex() {
        try {
            // 查看指定索引
            GetIndexResponse getIndexResponse = elasticsearchClient.indices().get(s -> s.index("products"));
            Map<String, IndexState> result = getIndexResponse.result();
            result.forEach((k, v) -> log.info("key = {},value = {}", k, v));

            // 查看全部索引
            IndicesResponse indicesResponse = elasticsearchClient.cat().indices();
            // 返回对象具体查看 co.elastic.clients.elasticsearch.cat.indices.IndicesRecord
            indicesResponse.valueBody().forEach(
                    info -> log.info("health:{}\n status:{} \n uuid:{} \n ", info.health(), info.status(), info.uuid())
            );
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
        return "success";
    }
}
