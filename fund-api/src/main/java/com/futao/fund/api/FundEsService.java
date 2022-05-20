package com.futao.fund.api;

import com.futao.fund.api.dto.FundDTO;

import java.util.List;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/19
 */
public interface FundEsService {
    Boolean spiderFetch();

    List<FundDTO> search(String fundName);
}
