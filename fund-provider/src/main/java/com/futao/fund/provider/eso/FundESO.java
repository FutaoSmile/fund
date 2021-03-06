package com.futao.fund.provider.eso;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.*;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/20
 */
@Getter
@Setter
// @TypeAlias("fund2") // 不会成为alias，而是将每个文档的 "_class" : "fund2"
@Setting(shards = 1, replicas = 1)
@Document(indexName = FundESO.INDEX_NAME, createIndex = true, dynamic = Dynamic.TRUE)
public class FundESO extends AuditingBase {

    public static final String INDEX_NAME = "fund";

    @Field(type = FieldType.Keyword)
    private String fundCode;
    public static final String FUND_CODE = "fundCode";

    @MultiField(
            mainField = @Field(type = FieldType.Text),
            otherFields = {
                    @InnerField(suffix = "keyword", type = FieldType.Keyword)
            }
    )
    private String fundName;
    public static final String FUND_NAME = "fundName";

    @Field(type = FieldType.Float)
    private Double unitNetWorth;
    public static final String UNIT_NET_WORTH = "unitNetWorth";

    @Field(type = FieldType.Date, format = DateFormat.epoch_millis)
    private Long date;
    public static final String DATE = "date";

    @Field(type = FieldType.Float)
    private Double dailyGrowthRate;
    public static final String DAILY_GROWTH_RATE = "dailyGrowthRate";

    @Field(type = FieldType.Float)
    private Double nearlyWeek;
    public static final String NEARLY_WEEK = "nearlyWeek";

    @Field(type = FieldType.Float)
    private Double nearlyMonth;
    public static final String NEARLY_MONTH = "nearlyMonth";

    @Field(type = FieldType.Float)
    private Double nearly3Month;
    public static final String NEARLY3_MONTH = "nearly3Month";

    @Field(type = FieldType.Float)
    private Double nearly6Month;
    public static final String NEARLY6_MONTH = "nearly6Month";

    @Field(type = FieldType.Float)
    private Double nearlyYear;
    public static final String NEARLY_YEAR = "nearlyYear";

    @Field(type = FieldType.Float)
    private Double nearly2Year;
    public static final String NEARLY2_YEAR = "nearly2Year";

    @Field(type = FieldType.Float)
    private Double nearly3Year;
    public static final String NEARLY_3_YEAR = "nearly3Year";

    @Field(type = FieldType.Float)
    private Double thisYear;
    public static final String THIS_YEAR = "thisYear";

    @Field(type = FieldType.Float)
    private Double sinceFounded;
    public static final String SINCE_FOUNDED = "sinceFounded";

    @Field(type = FieldType.Float)
    private Double handlingFee;
    public static final String HANDLING_FEE = "handlingFee";

    /**
     * 基金类型
     */
    @Field(type = FieldType.Keyword)
    private String fundType;
    /**
     * 风险等级
     */
    @Field(type = FieldType.Keyword)
    private String riskLevel;
    /**
     * 基金规模
     */
    @Field(type = FieldType.Keyword)
    private String totalValue;
    /**
     * 基金规模统计日期
     */
    @Field(type = FieldType.Keyword)
    private String totalValueDate;
    /**
     * 基金经理
     */
    @MultiField(
            mainField = @Field(type = FieldType.Text),
            otherFields = {
                    @InnerField(suffix = "keyword", type = FieldType.Keyword)
            }
    )
    private String manager;
    /**
     * 成立日期
     */
    @Field(type = FieldType.Keyword)
    private String establishmentDate;
    /**
     * 管理人，基金公司
     */
    @MultiField(
            mainField = @Field(type = FieldType.Text),
            otherFields = {
                    @InnerField(suffix = "keyword", type = FieldType.Keyword)
            }
    )
    private String fundManagerCompany;
    /**
     * 星级
     */
    @Field(type = FieldType.Short)
    private Integer star;

}
