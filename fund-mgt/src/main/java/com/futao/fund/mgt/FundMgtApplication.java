package com.futao.fund.mgt;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/19
 */
@EnableDubbo
@SpringBootApplication
public class FundMgtApplication {
    public static void main(String[] args) {
        SpringApplication.run(FundMgtApplication.class, args);
    }
}
