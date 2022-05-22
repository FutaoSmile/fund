package com.futao.fund.core.annos;

import java.lang.annotation.*;

/**
 * 不需要登录
 *
 * @author futaosmile@gmail.com
 * @date 2022/5/22
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface SkipUserAuth {
}
