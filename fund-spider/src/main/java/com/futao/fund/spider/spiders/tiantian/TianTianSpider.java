package com.futao.fund.spider.spiders.tiantian;

import com.futao.fund.spider.pageobject.FundPage;
import com.futao.fund.spider.parsers.TableParser;
import com.futao.fund.spider.utils.WebClientUtil;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.Asserts;

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

    public static void main(String[] args) {
        System.out.println("----------" + fundDetail("000536"));
    }

    public static FundPage fundDetail(String fundCode) {
        Asserts.notEmpty(fundCode, "基金代码");
        String url = "http://fund.eastmoney.com/" + fundCode + ".html";
        log.info("爬取地址:{}", url);
        try (WebClient webClient = WebClientUtil.getWebClient()) {
            HtmlPage page = webClient.getPage(url);
            // 定位到fundinfo这个div
            List<HtmlTableDataCell> tds = page.getByXPath("//div[@class='infoOfFund']//td");
            FundPage.FundPageBuilder fundPageBuilder = FundPage.builder().fundCode(fundCode);
            for (HtmlTableDataCell td : tds) {
                String contentStr = td.asNormalizedText();
                log.info("contentStr:{}", contentStr);
                String[] contentSplit = contentStr.split("：");
                String data = null;
                if (contentSplit.length >= 2) {
                    data = contentSplit[1];
                }
                if (contentStr.contains("基金类型")) {
                    List<String> dataList = Splitter.on("|")
                            .trimResults()
                            .splitToList(data);
                    fundPageBuilder.fundType(dataList.get(0));
                    fundPageBuilder.riskLevel(dataList.get(1));
                    continue;
                }
                if (contentStr.contains("基金规模")) {
                    String[] split = data.split("（");
                    fundPageBuilder.totalValue(split[0]);
                    fundPageBuilder.totalValueDate(split[1].replace("）", ""));
                    continue;
                }
                if (contentStr.contains("基金经理")) {
                    fundPageBuilder.manager(data);
                    continue;
                }
                if (contentStr.contains("成 立 日")) {
                    fundPageBuilder.establishmentDate(data);
                    continue;
                }
                if (contentStr.contains("管 理 人")) {
                    fundPageBuilder.fundManagerCompany(data);
                    continue;
                }

                if (contentStr.contains("基金评级")) {
                    // 获取不到，奇怪
                    // HtmlDivision starDiv = (HtmlDivision) td.getFirstByXPath("/div");
                    // String starClass = starDiv.getAttribute("class");
                    if (StringUtils.isNotEmpty(((HtmlDivision) td.getLastChild()).asNormalizedText())) {
                        fundPageBuilder.star(0);
                        continue;
                    }
                    String starClass = ((HtmlDivision) td.getLastChild()).getAttribute("class");
                    System.out.println(starClass);
                    char starString = starClass.charAt(starClass.length() - 1);
                    fundPageBuilder.star(Integer.parseInt(String.valueOf(starString)));
                    continue;
                }
            }
            return fundPageBuilder.build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
