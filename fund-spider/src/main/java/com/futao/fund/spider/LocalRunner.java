package com.futao.fund.spider;

import com.alibaba.fastjson.JSON;
import com.futao.fund.spider.pageobject.FundPage;
import com.futao.fund.spider.spiders.tiantian.TianTianSpider;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author futao
 * @date 2022/5/25
 */
@Slf4j
public class LocalRunner {
    public static void main(String[] args) {
        log.info("====={}", JSON.toJSONString(TianTianSpider.fetchTableValueByPage("http://fund.eastmoney.com/trade/gp.html", "tblite_gp", false)));
        log.info("====={}", JSON.toJSONString(TianTianSpider.fetchTableValueByPage("http://fund.eastmoney.com/trade/pg.html", "tblite_pg", false)));
        // log.info("====={}", JSON.toJSONString(TianTianSpider.fetchTableValueByPage("http://fund.eastmoney.com/trade/hh.html", "tblite_hh", false)));

    }
}
