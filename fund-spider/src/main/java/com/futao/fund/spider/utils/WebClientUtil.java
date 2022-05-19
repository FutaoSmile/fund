package com.futao.fund.spider.utils;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/19
 */
public class WebClientUtil {
    private WebClientUtil() {}

    /**
     * 默认的webclient
     * 使用完后需要关闭, AutoCloseable
     *
     * @return
     */
    public static WebClient getWebClient() {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        WebClientOptions options = webClient.getOptions();
        // 比如有些按钮的点击事件是通过js的控制和触发的，所以需要将js打开
        options.setJavaScriptEnabled(true);
        options.setCssEnabled(false);
        options.setThrowExceptionOnScriptError(false);
        options.setTimeout(20 * 1000);
        options.setSSLClientProtocols("TLSv1", "TLSv1.1", "TLSv1.2", "SSLv3");
        options.setUseInsecureSSL(true);
        webClient.getCookieManager().setCookiesEnabled(true);
        return webClient;
    }
}
