package com.futao.fund.core.util;

import java.util.UUID;

/**
 * @author ft
 * @date 2022/5/22
 */
public class StrUtils {
    private StrUtils() {
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
