package com.futao.fund.mgt.ao;

import com.futao.fund.api.FundEsService;
import com.futao.fund.api.dto.FundDTO;
import com.futao.fund.api.dto.query.FundQueryDTO;
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
        return fundEsService.spiderFetch2();
    }

    public List<FundDTO> search(FundQueryDTO fundName) {
        return fundEsService.search(fundName);
    }

    public List<FundDTO> searchByCriteria() {
        return fundEsService.searchByCriteria();
    }

    public List<FundDTO> searchByDsl() {
        return fundEsService.searchByDsl();
    }

    public FundDTO repositorySearch(String fundCode) {
        return fundEsService.repositorySearch(fundCode);
    }
}
