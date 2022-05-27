package com.futao.fund.api.dto;

import lombok.*;

import java.io.Serializable;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/20
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FundDTO implements Serializable {
    private String fundCode;
    private String fundName;
    private Double unitNetWorth;
    private Long date;
    private Double dailyGrowthRate;
    private Double nearlyWeek;
    private Double nearlyMonth;
    private Double nearly3Month;
    private Double nearly6Month;
    private Double nearlyYear;
    private Double nearly2Year;
    private Double nearly3Year;
    private Double thisYear;
    private Double sinceFounded;
    private Double handlingFee;
    private Long createDateTime;
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
