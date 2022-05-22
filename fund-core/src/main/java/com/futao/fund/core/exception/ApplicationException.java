package com.futao.fund.core.exception;

/**
 * @author futaosmile@gmail.com
 * @date 2021/9/1
 */
public class ApplicationException extends RuntimeException {
    private ApplicationException() {
    }

    private ApplicationException(String message) {
        super(message);
    }

    private ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public static ApplicationException ae(String message) {
        return new ApplicationException(message);
    }

    public static ApplicationException ae(String message, Throwable e) {
        return new ApplicationException(message, e);
    }
}
