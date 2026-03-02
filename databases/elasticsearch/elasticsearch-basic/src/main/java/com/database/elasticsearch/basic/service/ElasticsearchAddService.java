package com.database.elasticsearch.basic.service;

/**
 * @author shenlx
 * @description
 * @date 2026/2/15 21:21
 */
public interface ElasticsearchAddService {

    String createIndex();

    String createIndex1();

    String addDoc();


    String addBatch();

    String addBatchByIndexDoc();
}
