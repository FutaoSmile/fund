package com.futao.fund.provider.task;

import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
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

    @Bean
    public ApplicationRunner es() {
        return (args) -> {
            ClusterHealthResponse health = restHighLevelClient.cluster().health(new ClusterHealthRequest(), RequestOptions.DEFAULT);
            log.info("indices：{}", JSON.toJSONString(restHighLevelClient.indices()));
            log.info("tasks：{}", JSON.toJSONString(restHighLevelClient.tasks()));
            log.info("ES集群状态:{}", JSON.toJSONString(health));
        };
    }

}
