package com.futao.fund.core.annos;

import java.lang.annotation.*;

/**
 * 需要超管权限
 *
 * @author futaosmile@gmail.com
 * @date 2022/5/22
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface RequireSuperAdmin {

}
