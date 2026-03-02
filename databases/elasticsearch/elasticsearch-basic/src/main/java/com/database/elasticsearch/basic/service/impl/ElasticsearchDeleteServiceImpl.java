package com.database.elasticsearch.basic.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.DeleteRequest;
import co.elastic.clients.elasticsearch.core.DeleteResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.indices.DeleteIndexResponse;
import com.database.elasticsearch.basic.service.ElasticsearchDeleteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2026/2/16 21:50
 */
@Service
@Slf4j
public class ElasticsearchDeleteServiceImpl implements ElasticsearchDeleteService {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    /**
     * 根据id删除索引products下的文档doc
     */
    @Override
    public String delete() {
        try {
            // 创建删除请求对象
            DeleteRequest deleteRequest = new DeleteRequest.Builder()
                    .index("products")
                    .id("bk-5")
                    .build();
            elasticsearchClient.delete(deleteRequest);
            elasticsearchClient.indices().delete(s -> s.index("goods"));
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }

    /**
     * 删除文档
     *
     * @return
     * @throws IOException
     */
    @Override
    public String deleteDoc() {
        try {
            // 创建删除请求对象
            DeleteResponse deleteResponse = elasticsearchClient.delete(s -> s.index("products").id("bk-4"));
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }

    /**
     * 删除索引
     *
     * @return
     * @throws IOException
     */
    @Override
    public String deleteIndex() {
        try {
            DeleteIndexResponse deleteIndexResponse = elasticsearchClient.indices().delete(s -> s.index("goods"));
            log.info("删除索引操作结果：{}", deleteIndexResponse.acknowledged());
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }

    /**
     * 批量删除文档 方式一
     *
     * @return
     */
    @Override
    public String deleteStream() {
        try {
            // 方法1、use BulkOperation
            List<String> list = new ArrayList<>();
            list.add("1");
            list.add("2");
            List<BulkOperation> bulkOperations = new ArrayList<>();
            list.forEach(x ->
                    bulkOperations.add(
                            BulkOperation.of(
                                    b -> b.delete(c -> c.id(x)))
                    )
            );
            BulkResponse bulkResponse = elasticsearchClient.bulk(a -> a.index("users").operations(bulkOperations));
            bulkResponse.items().forEach(a ->
                    log.info("result = {}", a.result()));
            log.error("bulkResponse.errors() = {}", bulkResponse.errors());
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }

    @Override
    public String deleteBatch() {
        try {
            BulkRequest.Builder br = new BulkRequest.Builder();
            List<String> list = new ArrayList<>();
            list.add("3");
            list.add("4");
            list.forEach(
                    x ->
                            br.operations(op ->
                                    op.delete(c -> c.index("users").id(x)
                                    )
                            )
            );
            BulkResponse bulkResponse = elasticsearchClient.bulk(br.build());
            bulkResponse.items().forEach(
                    a ->
                            log.info("result = {}", a.result())
            );
            log.error("bulkResponse.errors() = {}", bulkResponse.errors());
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }
}
