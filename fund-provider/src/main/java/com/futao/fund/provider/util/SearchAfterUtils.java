package com.futao.fund.provider.util;

import com.futao.fund.core.spring.SpringContextHolder;
import com.futao.fund.provider.eso.AuditingBase;
import lombok.*;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/27
 */
public abstract class SearchAfterUtils {

    public static void search(Param param) {
        Assert.isTrue(param.getBatchSize() > 0, "每批次数据量大小必须大于0");
        exe(param, null);
    }

    private static void exe(Param param, Object[] searchAfter) {
        try {
            SearchRequest searchRequest = new SearchRequest(param.getIndexName());
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.query(new MatchAllQueryBuilder())
                    .size(param.getBatchSize())
                    .sort(SortBuilders.fieldSort(AuditingBase.ID)
                            .order(SortOrder.DESC));
            if (searchAfter != null) {
                sourceBuilder.searchAfter(searchAfter);
            }
            sourceBuilder.fetchSource(param.getSourceIncludes(), param.getSourceExcludes());
            searchRequest.source(sourceBuilder);
            SearchResponse searchResponse = SpringContextHolder.getBean(RestHighLevelClient.class).search(searchRequest, RequestOptions.DEFAULT);
            param.getConsumer().accept(searchResponse);
            // 当前批次获取的数据量
            int currentDataSize = searchResponse.getHits().getHits().length;
            if (currentDataSize == 0) {
                return;
            }
            Object[] curSearchAfter = searchResponse.getHits().getAt(currentDataSize - 1).getSortValues();
            if (param.getBatchSize() == currentDataSize) {
                exe(param, curSearchAfter);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Param {
        /**
         * 查询的索引名
         */
        private String indexName;
        /**
         * 需要查询的字段
         */
        private String[] sourceIncludes;
        /**
         * 排除的字段
         */
        private String[] sourceExcludes;
        /**
         * 每批次的大小
         */
        private int batchSize = 500;
        /**
         * 对结果的处理
         */
        private Consumer<SearchResponse> consumer;
    }
}
