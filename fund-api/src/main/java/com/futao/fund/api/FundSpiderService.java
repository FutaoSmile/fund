package com.futao.fund.api;

import com.futao.fund.api.dto.FundDTO;

import java.util.List;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/20
 */
public interface FundSpiderService {
    List<FundDTO> fetch();

    FundDTO detail(String funcCode);
}
