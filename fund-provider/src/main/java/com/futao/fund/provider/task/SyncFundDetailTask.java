package com.futao.fund.provider.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.futao.fund.api.FundSpiderService;
import com.futao.fund.api.dto.FundDTO;
import com.futao.fund.core.util.BeanUtils;
import com.futao.fund.provider.dao.FundRepository;
import com.futao.fund.provider.eso.FundESO;
import com.futao.fund.provider.util.SearchAfterUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/26
 */
@Slf4j
// @Component
@RequiredArgsConstructor
public class SyncFundDetailTask implements ApplicationRunner {

    @DubboReference(version = "1.0.0", retries = 0, timeout = 20000)
    private FundSpiderService fundSpiderService;

    private final FundRepository fundRepository;

    private final ElasticsearchOperations elasticsearchOperations;

    public Consumer<SearchResponse> consumer() {
        return searchResponse -> {
            List<UpdateQuery> updateQueries = new ArrayList<>();
            searchResponse.getHits()
                    .forEach(hit -> {
                        JSONObject jsonObject = JSON.parseObject(hit.getSourceAsString());
                        String fundCode = jsonObject.getString(FundESO.FUND_CODE);
                        FundDTO detail = fundSpiderService.detail(fundCode);
                        FundESO fundEso = BeanUtils.copyProperties(detail, FundESO.class);
                        fundEso.setId(hit.getId());
                        updateQueries.add(
                                UpdateQuery.builder(hit.getId())
                                        .withDocument(Document.from(JSON.parseObject(JSON.toJSONString(fundEso))))
                                        .build());
                    });
            log.info("批量更新:{}", JSON.toJSONString(updateQueries));
            if (CollectionUtils.isNotEmpty(updateQueries)) {
                elasticsearchOperations.bulkUpdate(updateQueries, IndexCoordinates.of(FundESO.INDEX_NAME));
            }
        };
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        SearchAfterUtils.search(SearchAfterUtils.Param.builder()
                .indexName(FundESO.INDEX_NAME)
                .batchSize(10)
                .consumer(consumer())
                .sourceIncludes(new String[]{FundESO.FUND_CODE})
                .build());
    }
}
