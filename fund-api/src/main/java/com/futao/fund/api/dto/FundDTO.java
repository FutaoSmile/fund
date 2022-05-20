package com.futao.fund.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/20
 */
@Getter
@Setter
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
}
