package com.futao.fund.core.exception;

import com.futao.fund.core.restful.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.env.Environment;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 统一异常处理器
 *
 * @author ft <futaosmile@gmail.com>
 * @date 2021/8/17
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @Autowired
    private Environment environment;

    private boolean isTestEnv() {
        return environment.acceptsProfiles("test");
    }

    /**
     * 逻辑异常
     *
     * @param e 异常对象
     * @return 错误提示
     */
    @ExceptionHandler(LogicException.class)
    public RestResult<String> logicExceptionHandler(LogicException e) {
        if (isTestEnv()) {
            log.error(e.getMessage(), e);
        }
        return RestResult.logicFail(e.getMessage(), e.getCode());
    }

    /**
     * 必要的参数没传
     *
     * @param e 异常对象
     * @return 错误提示
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public RestResult<String> missParam(MissingServletRequestParameterException e) {
        if (isTestEnv()) {
            log.error(e.getMessage(), e);
        }
        String parameterName = e.getParameterName();
        return RestResult.logicFail("参数[" + parameterName + "]不可为空");
    }

    /**
     * 处理404
     *
     * @param e 404异常
     * @return 错误提示
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public RestResult<String> noHandler(NoHandlerFoundException e) {
        if (isTestEnv()) {
            log.error(e.getMessage(), e);
        }
        return RestResult.logicFail("接口[" + e.getRequestURL() + "]不存在，请检查接口地址或者HttpMethod是否正确");
    }

    /**
     * 请求方式不支持
     *
     * @param e 异常
     * @return 错误提示
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public RestResult<String> methodNotSupport(HttpRequestMethodNotSupportedException e) {
        if (isTestEnv()) {
            log.error(e.getMessage(), e);
        }
        return RestResult.logicFail("当前接口不支持[" + e.getMethod() + "]方法，请使用" + Arrays.toString(e.getSupportedMethods()) + "方法");
    }

    /**
     * 参数校验1：@RequestParam上校验失败
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public RestResult<String> paramCheckError1(ConstraintViolationException e) {
        if (isTestEnv()) {
            log.error(e.getMessage(), e);
        }
        String message = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(";"));
        return RestResult.logicFail(message);
    }


    /**
     * 参数校验2：单独使用@Valid @Validated验证路径中请求实体
     */
    @ExceptionHandler(BindException.class)
    public RestResult<String> paramCheckError2(BindException e) {
        if (isTestEnv()) {
            log.error(e.getMessage(), e);
        }
        String message = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(";"));
        return RestResult.logicFail(message);
    }

    /**
     * 参数Valid失败
     *
     * @param e 异常
     * @return 错误提示
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RestResult<String> argument(MethodArgumentNotValidException e) {
        if (isTestEnv()) {
            log.error(e.getMessage(), e);
        }
        StringBuilder sb = new StringBuilder();
        e.getBindingResult().getAllErrors()
                .forEach(x -> sb.append(x.getDefaultMessage()).append(";"));
        return RestResult.logicFail(sb.toString());
    }

    /**
     * 系统未捕获的异常
     *
     * @param e 异常对象
     * @return 错误提示
     */
    @ExceptionHandler({Exception.class, ApplicationException.class})
    public RestResult<String> exceptionHandler(Exception e) {
        log.error("系统未捕获的异常", e);
        return RestResult.sysFail();
    }
}
