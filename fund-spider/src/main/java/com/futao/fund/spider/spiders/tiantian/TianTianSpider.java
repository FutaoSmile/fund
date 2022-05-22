package com.futao.fund.spider.spiders.tiantian;

import com.futao.fund.spider.pageobject.FundPage;
import com.futao.fund.spider.parsers.TableParser;
import com.futao.fund.spider.utils.WebClientUtil;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlLabel;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/19
 */
@Slf4j
public class TianTianSpider {
    /**
     * 通过抓取页面来解析数据
     *
     * @param url      页面地址
     * @param tableId  要抓取的表格的id
     * @param pageable 是否自动分页
     * @return 抓取到的数据
     */
    public static List<FundPage> fetchTableValueByPage(String url, String tableId, boolean pageable) {
        try (WebClient webClient = WebClientUtil.getWebClient()) {
            HtmlPage htmlPage = webClient.getPage(url);
            HtmlTable tableElement = htmlPage.getHtmlElementById(tableId);
            List<FundPage> fundList = TableParser.parse(tableElement, FundPage.class);
            if (pageable) {
                pageable(htmlPage, tableId, fundList);
            }
            return fundList;
        } catch (Exception e) {
            log.error("天天基金表格数据爬取异常", e);
        }
        return new ArrayList<>();
    }

    private static void pageable(HtmlPage htmlPage, String tableId, List<FundPage> fundList) throws IOException, InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        HtmlLabel nextPageLabel = htmlPage.getFirstByXPath("//label[contains(text(),'下一页')]");
        if (nextPageLabel != null && !"end".equals(nextPageLabel.getAttribute("class"))) {
            HtmlPage nextPage = nextPageLabel.click();
            HtmlTable tableElement2 = nextPage.getHtmlElementById(tableId);
            List<FundPage> curFundList = TableParser.parse(tableElement2, FundPage.class);
            fundList.addAll(curFundList);
            pageable(nextPage, tableId, fundList);
        }
    }
}
