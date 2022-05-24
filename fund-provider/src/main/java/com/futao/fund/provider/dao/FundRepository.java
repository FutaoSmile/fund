package com.futao.fund.provider.dao;

import com.futao.fund.provider.eso.FundESO;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/20
 */
public interface FundRepository extends ElasticsearchRepository<FundESO, String> {
    /**
     * https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#elasticsearch.query-methods.criterions
     *
     * @param fundName
     * @param dailyGrowthRate
     * @return
     */
    List<FundESO> findByFundNameAndDailyGrowthRateGreaterThan(String fundName, Double dailyGrowthRate);


    /**
     * 注意：只需要包含query的value，不需要外层query，与stringQuery类似
     * https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#elasticsearch.query-methods.at-query
     *
     * @param fundCode
     * @return
     */
    @Query(" {\n" +
            "    \"term\": {\n" +
            "      \"fundCode\": {\n" +
            "        \"value\": \"?0\"\n" +
            "      }\n" +
            "    }\n" +
            "  }")
    FundESO byFundCode(String fundCode);

}
