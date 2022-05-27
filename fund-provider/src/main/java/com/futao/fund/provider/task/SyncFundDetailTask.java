package com.futao.fund.provider.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.futao.fund.api.FundSpiderService;
import com.futao.fund.api.dto.FundDTO;
import com.futao.fund.provider.eso.FundESO;
import com.futao.fund.provider.util.SearchAfterUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/26
 */
@Slf4j
@Component
public class SyncFundDetailTask implements ApplicationRunner {

    @DubboReference(version = "1.0.0", retries = 0, timeout = 20000)
    private FundSpiderService fundSpiderService;

    public Consumer<SearchResponse> consumer() {
        return searchResponse -> {
            searchResponse.getHits()
                    .forEach(hit -> {
                        JSONObject jsonObject = JSON.parseObject(hit.getSourceAsString());
                        String fundCode = jsonObject.getString(FundESO.FUND_CODE);
                        FundDTO detail = fundSpiderService.detail(fundCode);
                        log.info("detail:{}", JSON.toJSONString(detail));
                    });
            System.out.println("--------a");
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
