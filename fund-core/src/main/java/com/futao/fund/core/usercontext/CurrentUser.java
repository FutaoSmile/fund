package com.futao.fund.core.usercontext;

import java.util.UUID;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/24
 */
public class CurrentUser {
    private static final ThreadLocal<String> USER_ID = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return UUID.randomUUID().toString();
        }
    };

    public static String s() {
        return USER_ID.get();
    }

    public static void set(String userId) {
        USER_ID.set(userId);
    }

    public static void clear() {
        USER_ID.remove();
    }
}
