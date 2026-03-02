package com.database.elasticsearch.basic.service;

/**
 * @author shenlx
 * @description
 * @date 2026/2/16 21:50
 */
public interface ElasticsearchDeleteService {

    String delete();

    String deleteDoc();

    String deleteIndex();

    String deleteStream();

    String deleteBatch();
}
