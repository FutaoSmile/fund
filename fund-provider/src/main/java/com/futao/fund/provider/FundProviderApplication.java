package com.futao.fund.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.config.EnableElasticsearchAuditing;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author futao
 * @date 2022/5/19
 */
@EnableElasticsearchRepositories(basePackages = {"com.futao.fund.provider.dao"})
// 审计
@EnableElasticsearchAuditing
@SpringBootApplication
public class FundProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(FundProviderApplication.class, args);
    }
}
