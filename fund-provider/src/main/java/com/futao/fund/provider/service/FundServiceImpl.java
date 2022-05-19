package com.futao.fund.provider.service;

import com.futao.fund.api.FundService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author futao
 * @date 2022/5/19
 */
@DubboService(version = "2.0.1")
public class FundServiceImpl implements FundService {

    @Value("${spring.application.name}")
    private String applicationName;
    @Override
    public String applicationName() {
        return applicationName;
    }
}
