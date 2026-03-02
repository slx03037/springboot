package com.database.elasticsearch.basic.service;

import co.elastic.clients.elasticsearch._types.aggregations.Buckets;
import co.elastic.clients.elasticsearch._types.aggregations.LongTermsBucket;
import com.database.elasticsearch.basic.entity.Product;
import com.database.elasticsearch.basic.entity.UserDO;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2026/2/15 21:40
 */
public interface ElasticsearchQueryService {

    String queryIndex();

    Product getDoc();

    ObjectNode getJson();

    Boolean booleanResponse();

    UserDO query();

    UserDO nestQuery();

    UserDO templateQuery();

    List<UserDO> pageQuery();

    List<UserDO> queryAllPageOrder();

    UserDO queryFilterField();

    List<UserDO> fuzzyQuery();

    UserDO highlightQuery();

    UserDO getMaxAgeUser();

    Buckets<LongTermsBucket> groupingQuery();

    void groupBySex();

}
