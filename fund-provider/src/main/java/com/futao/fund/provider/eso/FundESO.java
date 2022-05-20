package com.futao.fund.provider.eso;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.elasticsearch.annotations.*;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/20
 */
@Getter
@Setter
@TypeAlias("fund2") // 无效
@Setting(shards = 1, replicas = 1)
@Document(indexName = "fund", createIndex = true, dynamic = Dynamic.TRUE)
public class FundESO {
    @Id
    private String fundCode;

    @MultiField(
            mainField = @Field(type = FieldType.Text),
            otherFields = {
                    @InnerField(suffix = "keyword", type = FieldType.Keyword)
            }
    )
    private String fundName;

    @Field(type = FieldType.Float)
    private Double unitNetWorth;

    @Field(type = FieldType.Date, format = DateFormat.epoch_millis)
    private Long date;

    @Field(type = FieldType.Float)
    private Double dailyGrowthRate;

    @Field(type = FieldType.Float)
    private Double nearlyWeek;

    @Field(type = FieldType.Float)
    private Double nearlyMonth;

    @Field(type = FieldType.Float)
    private Double nearly3Month;

    @Field(type = FieldType.Float)
    private Double nearly6Month;

    @Field(type = FieldType.Float)
    private Double nearlyYear;

    @Field(type = FieldType.Float)
    private Double nearly2Year;

    @Field(type = FieldType.Float)
    private Double nearly3Year;

    @Field(type = FieldType.Float)
    private Double thisYear;

    @Field(type = FieldType.Float)
    private Double sinceFounded;

    @Field(type = FieldType.Float)
    private Double handlingFee;

    @Field(type = FieldType.Date, format = DateFormat.epoch_millis)
    private Long createDateTime;

}
