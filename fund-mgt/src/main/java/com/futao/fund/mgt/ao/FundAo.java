package com.futao.fund.mgt.ao;

import com.futao.fund.api.FundEsService;
import com.futao.fund.api.dto.FundDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author futao
 * @date 2022/5/19
 */
@Service
public class FundAo {

    @DubboReference(version = "${app.dubbo.service-version}", retries = 0, check = false)
    private FundEsService fundEsService;

    public Boolean spiderFetch() {
        return fundEsService.spiderFetch();
    }

    public List<FundDTO> search(String fundName) {
        return fundEsService.search(fundName);
    }
}
