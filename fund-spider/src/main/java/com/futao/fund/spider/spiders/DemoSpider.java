package com.futao.fund.spider.spiders;

import com.futao.fund.spider.spiders.tiantian.TianTianSpider;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/19
 */
public class DemoSpider {
    public static void main(String[] args) {
        String fetch = TianTianSpider.fetchTable("http://fund.eastmoney.com/trade/gp.html", "tblite_gp");
        // Table<String, String, Object> valueTable = HashBasedTable.<String, String, Object>create();
        // try (WebClient webClient = WebClientUtil.getWebClient()) {
        //     HtmlPage page = webClient.getPage("http://fund.eastmoney.com/data/fundranking.html");
        //     List<HtmlTableRow> byXPath = page.getHtmlElementById("tblite_gp").getByXPath("//tr");
        //     for (HtmlTableRow htmlTableRow : byXPath) {
        //         for (HtmlTableCell cell : htmlTableRow.getCells()) {
        //             System.out.println(cell.asNormalizedText());
        //             // valueTable.put()
        //         }
        //         System.out.println("");
        //     }
        // } catch (Exception e) {
        //     throw new RuntimeException(e);
        // }
    }
}
