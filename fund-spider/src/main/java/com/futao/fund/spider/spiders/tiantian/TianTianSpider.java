package com.futao.fund.spider.spiders.tiantian;

import com.alibaba.fastjson.JSON;
import com.futao.fund.spider.pageobject.FundPage;
import com.futao.fund.spider.parsers.TableParser;
import com.futao.fund.spider.utils.WebClientUtil;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlLabel;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
            log.info(JSON.toJSONString(parse, false));
            pageable(htmlPage, tableId);
        } catch (Exception e) {
            log.error("天天基金表格数据爬取异常", e);
        }
        return null;
    }

    private static void pageable(HtmlPage htmlPage, String tableId) throws IOException, InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        HtmlLabel nextPageLabel = htmlPage.getFirstByXPath("//label[contains(text(),'下一页')]");
        if (nextPageLabel != null && !"end".equals(nextPageLabel.getAttribute("class"))) {
            HtmlPage nextPage = nextPageLabel.click();
            HtmlTable tableElement2 = nextPage.getHtmlElementById(tableId);
            List<FundPage> parse2 = TableParser.parse(tableElement2, FundPage.class);
            log.info(JSON.toJSONString(parse2, false));
            pageable(nextPage, tableId);
        }
    }
}
