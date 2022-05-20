package com.futao.fund.mgt.ao;

import com.futao.fund.api.FundService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

/**
 * @author futao
 * @date 2022/5/19
 */
@Service
public class FundAo {

    @DubboReference(version = "${app.dubbo.service-version}", check = false)
    private FundService fundService;

    public String applicationName() {
        return fundService.applicationName();
    }
}
