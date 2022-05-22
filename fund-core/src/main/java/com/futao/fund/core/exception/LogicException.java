package com.futao.fund.core.exception;

import com.futao.fund.core.constant.ResponseCodes;
import lombok.Getter;

/**
 * 逻辑异常
 *
 * @author ft <futao@gmail.com>
 * @date 2021/8/17
 */
@Getter
public class LogicException extends RuntimeException {
    private Integer code;

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    private LogicException(String message) {
        super(message);
    }

    public static LogicException le(String msg, int code) {
        LogicException logicException = new LogicException(msg);
        logicException.code = code;
        return logicException;
    }

    public static LogicException le(String msg) {
        return le(msg, ResponseCodes.LOGIC_FAIL);
    }
}
