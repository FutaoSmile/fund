package com.futao.fund.core.constant;

/**
 * 响应状态码
 *
 * @author ft <futaosmile@gmail.com>
 * @since 2022/5/22
 */
public class ResponseCodes {
    /**
     * 正常
     */
    public static final int SUCCESS = 0;
    /**
     * 逻辑异常
     */
    public static final int LOGIC_FAIL = -1;
    /**
     * 系统异常
     */
    public static final int SYSTEM_ERROR_FAIL = 500;

    private ResponseCodes() {
    }
}
