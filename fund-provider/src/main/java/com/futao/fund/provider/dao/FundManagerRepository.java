package com.futao.fund.provider.dao;

import com.futao.fund.provider.eso.FundManagerESO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/26
 */
public interface FundManagerRepository extends ElasticsearchRepository<FundManagerESO, String> {
}
