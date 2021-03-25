package com.x.demo.es.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * @author Leo
 */
@RestController
@RequestMapping("es/demo")
@Slf4j
public class EsDemoController {


    @GetMapping("query")
    public String query() throws IOException {

        String hostName = "localhost";
        int port = 9200;
        String indexName = "es_search";

        //创建Client
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost(hostName, port, "http"))
        );

        //构建匹配条件
        QueryBuilder queryBuilderAll = QueryBuilders.matchAllQuery();

        //组合匹配条件
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(queryBuilderAll);

        //创建查询
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.fetchSource(new String[]{"id", "name", "content"}, null);
        sourceBuilder.query(boolQueryBuilder);

        sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
        sourceBuilder.sort(new FieldSortBuilder("_id").order(SortOrder.DESC));

        //创建搜索Request
        SearchRequest request = new SearchRequest(indexName);
        request.searchType("dfs_query_then_fetch");
        request.source(sourceBuilder);

        //解析反馈结果
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        for (SearchHit hit : hits) {
            Map tempSource = hit.getSourceAsMap();
            log.info(tempSource.toString());
        }

        return "success";
    }
}
