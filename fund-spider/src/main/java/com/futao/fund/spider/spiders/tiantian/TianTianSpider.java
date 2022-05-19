package com.futao.fund.spider.spiders.tiantian;

import com.alibaba.fastjson.JSON;
import com.futao.fund.spider.pageobject.FundPage;
import com.futao.fund.spider.parsers.TableParser;
import com.futao.fund.spider.utils.WebClientUtil;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/19
 */
@Slf4j
public class TianTianSpider {
    public static String fetchTable(String url, String tableId) {
        try (WebClient webClient = WebClientUtil.getWebClient()) {
            HtmlPage htmlPage = webClient.getPage(url);
            HtmlTable tableElement = htmlPage.getHtmlElementById(tableId);
            List<FundPage> parse = TableParser.parse(tableElement, FundPage.class);
            log.info(JSON.toJSONString(parse, true));

        } catch (Exception e) {
            log.error("天天基金表格数据爬取异常", e);
        }
        return null;
    }
}
