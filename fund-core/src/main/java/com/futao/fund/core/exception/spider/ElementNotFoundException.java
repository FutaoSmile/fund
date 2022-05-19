package com.futao.fund.core.exception.spider;

import lombok.Getter;
import lombok.Setter;

/**
 * 元素不存在异常
 *
 * @author futaosmile@gmail.com
 * @date 2022/5/19
 */
@Getter
@Setter
public class ElementNotFoundException extends RuntimeException {

    private String url;
    private String elementName;

    public ElementNotFoundException() {
        super();
    }

    public ElementNotFoundException(String url, String elementName) {
        super("页面[" + url + "]未找到元素:[" + elementName + "]");
    }

    public ElementNotFoundException(String url, String elementName, Throwable cause) {
        super("页面[" + url + "]未找到元素:[" + elementName + "]", cause);
    }

    public ElementNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ElementNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
