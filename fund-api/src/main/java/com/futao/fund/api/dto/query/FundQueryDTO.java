package com.futao.fund.api.dto.query;

import com.futao.fund.api.dto.query.base.Pageable;
import com.futao.fund.api.dto.query.base.Sortable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/23
 */
@Getter
@Setter
public class FundQueryDTO implements Serializable, Sortable, Pageable {
    private String fundName;
    private String fundCode;
    private Long date;
    private String sortFields;
    private Integer pageNo;
    private Integer pageSize;

    @Override
    public String getSortFields() {
        return sortFields;
    }

    @Override
    public Integer getPageNum() {
        return pageNo;
    }

    @Override
    public Integer getPageSize() {
        return pageSize;
    }
}
