package com.futao.fund.server.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/30
 */
@SpringBootApplication
@EnableAdminServer
public class FundMonitorApplication {
    public static void main(String[] args) {
        SpringApplication.run(FundMonitorApplication.class, args);
    }
}
