package com.futao.fund.provider.task;

import com.alibaba.fastjson.JSON;
import com.futao.fund.provider.eso.FundESO;
import com.futao.fund.provider.util.ScrollUtils;
import com.futao.fund.provider.util.SearchAfterUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/20
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StartTask {

    private final RestHighLevelClient restHighLevelClient;

    // @Bean
    public ApplicationRunner es() {
        return (args) -> {
            ClusterHealthResponse health = restHighLevelClient.cluster().health(new ClusterHealthRequest(), RequestOptions.DEFAULT);
            log.info("indices：{}", JSON.toJSONString(restHighLevelClient.indices()));
            log.info("tasks：{}", JSON.toJSONString(restHighLevelClient.tasks()));
            log.info("ES集群状态:{}", JSON.toJSONString(health));
        };
    }

    // @Bean
    public ApplicationRunner runner() {
        return args -> {
            ScrollUtils.Param<FundESO> fundESOParam = new ScrollUtils.Param<>();
            fundESOParam.setBatchSize(10);
            fundESOParam.setIndexName(FundESO.INDEX_NAME);
            fundESOParam.setQueryBuilder(new MatchAllQueryBuilder());
            fundESOParam.setTClass(FundESO.class);
            fundESOParam.setConsumer(fundESOSearchHit -> log.info("scroll result:{}", JSON.toJSONString(fundESOSearchHit.getContent())));
            ScrollUtils.scroll(fundESOParam);
        };
    }

    @SneakyThrows
    // @Bean
    public ApplicationRunner runner2() {
        return args -> {
            SearchAfterUtils.search(SearchAfterUtils.Param.builder()
                    .indexName(FundESO.INDEX_NAME)
                    .batchSize(10)
                    .queryBuilder(new MatchAllQueryBuilder())
                    .consumer(x -> {
                        for (SearchHit hit : x.getHits()) {
                            log.info("search after:{}", hit.getSourceAsString());
                        }
                    })
                    .build());
        };
    }


}
