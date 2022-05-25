package com.futao.fund.spider.pageobject;

import com.futao.fund.spider.annotations.ColumnName;
import lombok.*;

import java.io.Serializable;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/19
 */
@Getter
@Setter
@ToString
@Builder
public class FundPage implements PageObject, Serializable {
    @ColumnName("基金代码")
    private String fundCode;
    @ColumnName("基金名称")
    private String fundName;
    @ColumnName("单位净值")
    private String unitNetWorth;
    @ColumnName("日期")
    private String date;
    @ColumnName("日增长率")
    private String dailyGrowthRate;
    @ColumnName("近1周")
    private String nearlyWeek;
    @ColumnName("近1月")
    private String nearlyMonth;
    @ColumnName("近3月")
    private String nearly3Month;
    @ColumnName("近6月")
    private String nearly6Month;
    @ColumnName("近1年")
    private String nearlyYear;
    @ColumnName("近2年")
    private String nearly2Year;
    @ColumnName("近3年")
    private String nearly3Year;
    @ColumnName("今年来")
    private String thisYear;
    @ColumnName("成立来")
    private String sinceFounded;
    @ColumnName("手续费")
    private String handlingFee;
    /**
     * 基金类型
     */
    private String fundType;
    /**
     * 风险等级
     */
    private String riskLevel;
    /**
     * 基金规模
     */
    private String totalValue;
    /**
     * 基金规模统计日期
     */
    private String totalValueDate;
    /**
     * 基金经理
     */
    private String manager;
    /**
     * 成立日期
     */
    private String establishmentDate;
    /**
     * 管理人，基金公司
     */
    private String fundManagerCompany;
    /**
     * 星级
     */
    private Integer star;
}
