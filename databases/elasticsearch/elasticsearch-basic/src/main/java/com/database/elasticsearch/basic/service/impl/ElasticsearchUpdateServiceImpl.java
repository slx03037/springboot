package com.database.elasticsearch.basic.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.UpdateRequest;
import co.elastic.clients.elasticsearch.core.UpdateResponse;
import com.database.elasticsearch.basic.entity.Product;
import com.database.elasticsearch.basic.service.ElasticsearchUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author shenlx
 * @description
 * @date 2026/2/15 21:29
 */
@Service
@Slf4j
public class ElasticsearchUpdateServiceImpl implements ElasticsearchUpdateService {
    @Autowired
    private ElasticsearchClient elasticsearchClient;

    /**
     * 修改products索引下的id为bk-1
     */

    @Override
    public String update()  {
        try {
            Product product = new Product("bk-01", "update", 188.01);
            // 创建修改对象
            UpdateRequest updateRequest = new UpdateRequest.Builder().index("products")
                    .id("bk-1")
                    .doc(product)
                    .build();
            elasticsearchClient.update(updateRequest, Product.class);
        }catch (Exception e){
            return "error";
        }
        return "success";
    }


    @Override
    public String updateDoc()  {
       try{
           Product product = new Product("bk-01", "update", 188.01);
           // 创建修改对象
           // 构建修改文档的请求
           UpdateResponse<Product> response = elasticsearchClient.update(e -> e
                   .index("products")
                   .id("bk-3")
                   .doc(product),Product.class);
           log.info("update result:{}",response.result());
       }catch (Exception e){
           return "error";
       }
        return "success";
    }
}
