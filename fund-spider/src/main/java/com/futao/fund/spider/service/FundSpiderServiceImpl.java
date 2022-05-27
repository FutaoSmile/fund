package com.futao.fund.spider.service;

import com.futao.fund.api.FundSpiderService;
import com.futao.fund.api.dto.FundDTO;
import com.futao.fund.core.util.BeanUtils;
import com.futao.fund.spider.pageobject.FundPage;
import com.futao.fund.spider.spiders.tiantian.TianTianSpider;
import com.futao.fund.spider.utils.NumberUtil;
import com.futao.fund.spider.utils.TimeUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/20
 */
@DubboService(version = "1.0.0", timeout = 300000)
public class FundSpiderServiceImpl implements FundSpiderService {

    @Override
    public List<FundDTO> fetch() {
        List<FundPage> fundPageList = TianTianSpider.fetchTableValueByPage("http://fund.eastmoney.com/trade/gp.html", "tblite_gp", false);
        if (CollectionUtils.isNotEmpty(fundPageList)) {
            return fundPageList.stream()
                    .map(x -> FundDTO.builder()
                            .fundCode(x.getFundCode())
                            .fundName(x.getFundName())
                            .unitNetWorth(NumberUtil.parseString2Double(x.getUnitNetWorth()))
                            .date(TimeUtil.toTimestamp(TimeUtil.currentDate()))
                            .dailyGrowthRate(NumberUtil.parseString2Double(x.getDailyGrowthRate()))
                            .nearlyWeek(NumberUtil.parseString2Double(x.getNearlyWeek()))
                            .nearlyMonth(NumberUtil.parseString2Double(x.getNearlyMonth()))
                            .nearly3Month(NumberUtil.parseString2Double(x.getNearly3Month()))
                            .nearly6Month(NumberUtil.parseString2Double(x.getNearly6Month()))
                            .nearlyYear(NumberUtil.parseString2Double(x.getNearlyYear()))
                            .nearly2Year(NumberUtil.parseString2Double(x.getNearly2Year()))
                            .nearly3Year(NumberUtil.parseString2Double(x.getNearly3Year()))
                            .thisYear(NumberUtil.parseString2Double(x.getThisYear()))
                            .sinceFounded(NumberUtil.parseString2Double(x.getSinceFounded()))
                            .handlingFee(NumberUtil.parseString2Double(x.getHandlingFee()))
                            .build()
                    ).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public FundDTO detail(String funcCode) {
        FundPage fundPage = TianTianSpider.fundDetail(funcCode);
        return BeanUtils.copyProperties(fundPage, FundDTO.class);
    }
}
