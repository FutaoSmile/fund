package com.futao.fund.provider.service;

import com.futao.fund.api.FundEsService;
import com.futao.fund.api.FundSpiderService;
import com.futao.fund.api.dto.FundDTO;
import com.futao.fund.provider.eso.FundESO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author futao
 * @date 2022/5/19
 */
@DubboService(version = "1.0.0", timeout = 300000)
public class FundEsServiceImpl implements FundEsService {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @DubboReference(version = "1.0.0", retries = 0, check = false)
    private FundSpiderService fundSpiderService;

    @Override
    public Boolean spiderFetch() {
        List<FundDTO> fetchResult = fundSpiderService.fetch();
        if (CollectionUtils.isNotEmpty(fetchResult)) {
            List<IndexQuery> indexQueryList = fetchResult.stream()
                    .map(x -> new IndexQueryBuilder()
                            .withObject(x)
                            .withId(x.getFundCode())
                            .build()).collect(Collectors.toList());
            elasticsearchOperations.bulkIndex(indexQueryList, FundESO.class);
        }
        return Boolean.TRUE;
    }

    @Override
    public List<FundDTO> search(String fundName) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        if (StringUtils.isNotBlank(fundName)) {
            nativeSearchQueryBuilder.withQuery(QueryBuilders.matchQuery("fundName", fundName));
        }
        // FieldSortBuilder dailyGrowthRateSort = new FieldSortBuilder("dailyGrowthRate");
        // nativeSearchQueryBuilder.withSorts(dailyGrowthRateSort)
        nativeSearchQueryBuilder.withMaxResults(100);
        SearchHits<FundESO> search = elasticsearchOperations.search(nativeSearchQueryBuilder.build(), FundESO.class);
        return search.getSearchHits()
                .stream()
                .map(x -> {
                    FundESO content = x.getContent();
                    FundDTO fundDto = new FundDTO();
                    BeanUtils.copyProperties(content, fundDto);
                    return fundDto;
                }).collect(Collectors.toList());
    }
}
