package com.futao.fund.spider.service;

import com.futao.fund.api.FundSpiderService;
import com.futao.fund.api.dto.FundDTO;
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
        List<FundPage> fundPageList = TianTianSpider.fetchTableValueByPage("http://fund.eastmoney.com/trade/gp.html", "tblite_gp", true);
        if (CollectionUtils.isNotEmpty(fundPageList)) {
            return fundPageList.stream()
                    .map(x -> new FundDTO(
                            x.getFundCode(),
                            x.getFundName(),
                            NumberUtil.parseString2Double(x.getUnitNetWorth()),
                            TimeUtil.toTimestamp(TimeUtil.currentDate()),
                            NumberUtil.parseString2Double(x.getDailyGrowthRate()),
                            NumberUtil.parseString2Double(x.getNearlyWeek()),
                            NumberUtil.parseString2Double(x.getNearlyMonth()),
                            NumberUtil.parseString2Double(x.getNearly3Month()),
                            NumberUtil.parseString2Double(x.getNearly6Month()),
                            NumberUtil.parseString2Double(x.getNearlyYear()),
                            NumberUtil.parseString2Double(x.getNearly2Year()),
                            NumberUtil.parseString2Double(x.getNearly3Year()),
                            NumberUtil.parseString2Double(x.getThisYear()),
                            NumberUtil.parseString2Double(x.getSinceFounded()),
                            NumberUtil.parseString2Double(x.getHandlingFee()),
                            TimeUtil.toTimestamp(TimeUtil.currentDateTime())
                    )).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
