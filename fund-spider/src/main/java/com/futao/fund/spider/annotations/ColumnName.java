package com.futao.fund.spider.annotations;

import java.lang.annotation.*;

/**
 * 列名
 *
 * @author futaosmile@gmail.com
 * @date 2022/5/19
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ColumnName {
    String value();
}
