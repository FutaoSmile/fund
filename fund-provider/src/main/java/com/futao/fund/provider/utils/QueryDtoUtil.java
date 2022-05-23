package com.futao.fund.provider.utils;

import com.futao.fund.api.dto.query.base.Pageable;
import com.futao.fund.api.dto.query.base.Sortable;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/23
 */
public class QueryDtoUtil {
    public static void pageable(NativeSearchQueryBuilder nativeSearchQueryBuilder, Pageable pageable) {
        Integer pageNum = 0;
        Integer pageSize = 10;
        if (pageable != null) {
            pageNum = pageable.getPageNum();
            pageSize = pageable.getPageSize();
        }
        pageNum = pageNum == null ? 0 : pageNum < 0 ? 0 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize <= 0 ? 10 : pageSize;
        pageSize = pageSize > 500 ? 500 : pageSize;
        nativeSearchQueryBuilder.withPageable(PageRequest.of(pageNum, pageSize));
    }

    public static void sortable(NativeSearchQueryBuilder nativeSearchQueryBuilder, Sortable sortable) {
        if (sortable != null) {
            String sortFields = sortable.getSortFields();
            if (StringUtils.isNotEmpty(sortFields)) {
                String[] sortFieldsArr = sortFields.split(",");
                for (String sortField : sortFieldsArr) {
                    String[] sortFieldArr = sortField.split(":");
                    nativeSearchQueryBuilder.withSorts(SortBuilders.fieldSort(sortFieldArr[0]).order(SortOrder.fromString(sortFieldArr[1])));
                }
            }
        }
    }
}
