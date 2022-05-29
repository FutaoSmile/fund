package com.futao.fund.provider.util;

import com.futao.fund.core.spring.SpringContextHolder;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHitsIterator;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilterBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.function.Consumer;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/29
 */
@Slf4j
public abstract class ScrollUtils {

    public static <T> void scroll(ScrollUtils.Param<T> param) {
        ElasticsearchRestTemplate elasticsearchRestTemplate = SpringContextHolder.getBean(ElasticsearchRestTemplate.class);
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(param.getQueryBuilder())
                .withSourceFilter(new FetchSourceFilterBuilder()
                        .withIncludes(param.getSourceIncludes())
                        .withExcludes(param.getSourceExcludes())
                        .build())
                .withPageable(PageRequest.ofSize(param.getBatchSize()))
                .build();

        SearchHitsIterator<T> tSearchHitsIterator = elasticsearchRestTemplate.searchForStream(nativeSearchQuery, param.getTClass(), IndexCoordinates.of(param.getIndexName()));
        while (tSearchHitsIterator.hasNext()) {
            SearchHit<T> tSearchHit = tSearchHitsIterator.next();
            param.getConsumer().accept(tSearchHit);
        }
        tSearchHitsIterator.close();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Param<T> {
        private Class<T> tClass;
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
        private Consumer<SearchHit<T>> consumer;
        /**
         * 查询条件
         */
        private QueryBuilder queryBuilder;
    }
}
