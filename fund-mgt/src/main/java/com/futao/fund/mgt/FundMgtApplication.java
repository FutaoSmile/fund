package com.futao.fund.mgt;

import com.futao.fund.mgt.config.AppProperties;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/19
 */
@ComponentScan({"com.futao.fund.mgt", "com.futao.fund.core"})
@EnableDubbo
@EnableConfigurationProperties({AppProperties.class})
@SpringBootApplication
public class FundMgtApplication {
    public static void main(String[] args) {
        SpringApplication.run(FundMgtApplication.class, args);
    }
}
