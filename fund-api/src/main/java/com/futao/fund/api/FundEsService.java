package com.futao.fund.api;

import com.futao.fund.api.dto.FundDTO;
import com.futao.fund.api.dto.query.FundQueryDTO;

import java.util.List;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/19
 */
public interface FundEsService {
    Boolean spiderFetch2();

    Boolean spiderFetch();

    List<FundDTO> search(FundQueryDTO fundName);

    List<FundDTO> searchByCriteria();

    List<FundDTO> searchByDsl();

    FundDTO repositorySearch(String fundCode);
}
