package com.futao.fund.provider.eso;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.*;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/26
 */
@Getter
@Setter
@Setting(shards = 1, replicas = 1)
@Document(indexName = FundManagerESO.INDEX_NAME, createIndex = true, dynamic = Dynamic.TRUE)
public class FundManagerESO extends AuditingBase {

    public static final String INDEX_NAME = "fund_manager";

    @MultiField(
            mainField = @Field(type = FieldType.Text, index = true, store = false, fielddata = false),
            otherFields = {
                    @InnerField(suffix = "keyword", type = FieldType.Keyword)
            }
    )
    private String realName;

}


