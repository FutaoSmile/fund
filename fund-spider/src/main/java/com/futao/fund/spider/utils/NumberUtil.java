package com.futao.fund.spider.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/20
 */
public class NumberUtil {
    private NumberUtil() {}

    public static Double parseString2Double(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        if (value.contains("-")) {
            return Double.valueOf(0D);
        }
        return Double.valueOf(value.replace("%", StringUtils.EMPTY).trim());
    }
}
