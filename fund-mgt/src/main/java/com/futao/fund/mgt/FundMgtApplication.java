package com.futao.fund.mgt;

import com.futao.fund.mgt.config.AppProperties;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/19
 */
@EnableDubbo
@EnableConfigurationProperties({AppProperties.class})
@SpringBootApplication
public class FundMgtApplication {
    public static void main(String[] args) {
        SpringApplication.run(FundMgtApplication.class, args);
    }
}
