package com.futao.fund.provider.dao;

import com.futao.fund.provider.eso.FundESO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/20
 */
public interface FundRepository extends ElasticsearchRepository<FundESO, String> {
}
