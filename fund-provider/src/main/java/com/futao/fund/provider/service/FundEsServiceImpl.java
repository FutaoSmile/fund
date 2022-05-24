package com.futao.fund.provider.service;

import com.alibaba.fastjson.JSONObject;
import com.futao.fund.api.FundEsService;
import com.futao.fund.api.FundSpiderService;
import com.futao.fund.api.dto.FundDTO;
import com.futao.fund.api.dto.query.FundQueryDTO;
import com.futao.fund.provider.dao.FundRepository;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#elasticsearch.operations
 *
 * @author futao
 * @date 2022/5/19
 */
@Slf4j
@DubboService(version = "1.0.0", timeout = 300000)
public class FundEsServiceImpl implements FundEsService {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    private FundRepository fundRepository;

    @DubboReference(version = "1.0.0", retries = 0, check = false)
    private FundSpiderService fundSpiderService;

    @Override
    public Boolean spiderFetch() {
        List<FundDTO> fetchResult = fundSpiderService.fetch();
        if (CollectionUtils.isNotEmpty(fetchResult)) {
            List<IndexQuery> indexQueryList = fetchResult.stream()
                    .map(x -> new IndexQueryBuilder()
                            .withObject(x)
                            .build())
                    .collect(Collectors.toList());
            elasticsearchOperations.bulkIndex(indexQueryList, FundESO.class);
        }
        return Boolean.TRUE;
    }

    /**
     * https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#elasticsearch.operations.nativesearchquery
     *
     * @param fundQueryDto
     * @return
     */
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

    /**
     * https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#elasticsearch.operations.criteriaquery
     *
     * @return
     */
    @Override
    public List<FundDTO> searchByCriteria() {
        // Criteria criteria = new Criteria(FundESO.DAILY_GROWTH_RATE).greaterThanEqual(3).lessThan(5);
        Criteria criteria = new Criteria(FundESO.FUND_NAME).is("前海")
                .and(FundESO.DAILY_GROWTH_RATE).greaterThanEqual(1);

        log.info("criteria query: {}", criteria);
        Query query = new CriteriaQuery(criteria);
        SearchHits<FundESO> search = elasticsearchOperations.search(query, FundESO.class);
        return search.getSearchHits()
                .stream()
                .map(x -> {
                    FundESO content = x.getContent();
                    FundDTO fundDto = new FundDTO();
                    BeanUtils.copyProperties(content, fundDto);
                    return fundDto;
                }).collect(Collectors.toList());
    }

    /**
     * https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#elasticsearch.operations.stringquery
     *
     * @return
     */
    @Override
    public List<FundDTO> searchByDsl() {
        JSONObject dslQuery = new JSONObject();
        // 不需要最外层的query
        dslQuery.fluentPut("match", new JSONObject().fluentPut(FundESO.FUND_NAME, "前海"));
        // sort 和 page 需要单独传
        // .fluentPut("sort", new JSONArray().fluentAdd(new JSONObject().fluentPut(FundESO.DAILY_GROWTH_RATE, new JSONObject().fluentPut("order", "desc"))))
        // .fluentPut("from", 1)
        // .fluentPut("size", 20);
        String source = dslQuery.toJSONString();
        log.info("search dsl: {}", source);
        Query query = new StringQuery(source, PageRequest.of(0, 20), Sort.by(Sort.Order.desc(FundESO.DAILY_GROWTH_RATE)));
        SearchHits<FundESO> search = elasticsearchOperations.search(query, FundESO.class);
        return search.getSearchHits()
                .stream()
                .map(x -> {
                    FundESO content = x.getContent();
                    FundDTO fundDto = new FundDTO();
                    BeanUtils.copyProperties(content, fundDto);
                    return fundDto;
                }).collect(Collectors.toList());
    }


    @Override
    public FundDTO repositorySearch(String fundCode) {
        FundESO fundESO = fundRepository.byFundCode(fundCode);
        return com.futao.fund.core.util.BeanUtils.copyProperties(fundESO, FundDTO.class);
    }
}
