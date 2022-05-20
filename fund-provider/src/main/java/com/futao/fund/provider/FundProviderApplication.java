package com.futao.fund.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author futao
 * @date 2022/5/19
 */
@EnableElasticsearchRepositories(basePackages = {"com.futao.fund.provider.dao"})
@SpringBootApplication
public class FundProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(FundProviderApplication.class, args);
    }
}
