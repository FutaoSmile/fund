package com.futao.fund.core.util;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/24
 */
public class BeanUtils {
    private BeanUtils() {}

    public static <T> T copyProperties(Object source, Class<T> targetType) {
        return BeanUtils.copyProperties(source, targetType, (String) null);
    }

    public static <T> T copyProperties(Object source, Class<T> targetType, String... ignoreProperties) {
        try {
            T target = targetType.newInstance();
            org.springframework.beans.BeanUtils.copyProperties(source, target, ignoreProperties);
            return target;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
