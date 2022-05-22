package com.futao.fund.core.util;

import com.alibaba.fastjson.JSONObject;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/22
 */
public class UserUtils {

    private UserUtils() {
    }

    public static JSONObject currentUser() {
        return new JSONObject().fluentPut("name", "李达康").fluentPut("adminId", 123).fluentPut("administrator", true);
    }

    public static String username() {
        return currentUser().getString("name");
    }

    public static String adminId() {
        return currentUser().getString("adminId");
    }

    public static boolean isAdministrator() {
        return currentUser().getBooleanValue("administrator");
    }
}
