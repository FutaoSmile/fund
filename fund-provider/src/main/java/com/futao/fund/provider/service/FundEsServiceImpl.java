package com.futao.fund.provider.service;

import com.futao.fund.api.FundEsService;
import com.futao.fund.api.FundSpiderService;
import com.futao.fund.api.dto.FundDTO;
import com.futao.fund.api.dto.query.FundQueryDTO;
import com.futao.fund.provider.eso.FundESO;
import com.futao.fund.provider.utils.QueryDtoUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author futao
 * @date 2022/5/19
 */
@Slf4j
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
                            .build()).collect(Collectors.toList());
            elasticsearchOperations.bulkIndex(indexQueryList, FundESO.class);
        }
        return Boolean.TRUE;
    }

    @Override
    public List<FundDTO> search(FundQueryDTO fundQueryDto) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        if (fundQueryDto != null) {
            String fundCode = fundQueryDto.getFundCode();
            String fundName = fundQueryDto.getFundName();
            Long date = fundQueryDto.getDate();

            if (StringUtils.isNotBlank(fundName)) {
                nativeSearchQueryBuilder.withQuery(QueryBuilders.matchQuery("fundName", fundName));
            }
            if (StringUtils.isNotBlank(fundCode)) {
                nativeSearchQueryBuilder.withQuery(QueryBuilders.termQuery("fundCode", fundCode));
            }
            if (date != null) {
                nativeSearchQueryBuilder.withQuery(QueryBuilders.termQuery("date", date));
            }
            QueryDtoUtil.pageable(nativeSearchQueryBuilder, fundQueryDto);
            QueryDtoUtil.sortable(nativeSearchQueryBuilder, fundQueryDto);
        }

        NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.build();
        QueryBuilder query = nativeSearchQuery.getQuery();
        if (query != null) {
            log.info("ES查询：{}", query);
        }
        SearchHits<FundESO> search = elasticsearchOperations.search(nativeSearchQuery, FundESO.class);
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
