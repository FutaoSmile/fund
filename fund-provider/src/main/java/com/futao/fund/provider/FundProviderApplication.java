package com.futao.fund.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.config.EnableElasticsearchAuditing;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author futao
 * @date 2022/5/19
 */
@EnableScheduling
@EnableElasticsearchRepositories(basePackages = {"com.futao.fund.provider.dao"})
// ES审计
@EnableElasticsearchAuditing
@SpringBootApplication(scanBasePackages = {"com.futao.fund.core", "com.futao.fund.provider"})
public class FundProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(FundProviderApplication.class, args);
    }
}
