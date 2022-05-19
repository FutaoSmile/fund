package com.futao.fund.spider.annotations.processor;

import com.futao.fund.spider.annotations.ColumnName;
import com.futao.fund.spider.pageobject.PageObject;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/19
 */
public class ColumnNameProcessor {
    private ColumnNameProcessor() {}

    /**
     * 获取PageObject所有字段对应列明和字段关系
     *
     * @param type
     * @param <T>
     * @return
     */
    public static <T extends PageObject> Map<String, Field> parse2Map(Class<T> type) {
        HashMap<String, Field> fieldMap = new HashMap<>();
        for (Field declaredField : type.getDeclaredFields()) {
            if (declaredField.isAnnotationPresent(ColumnName.class)) {
                fieldMap.put(declaredField.getAnnotation(ColumnName.class).value(), declaredField);
            }
        }
        return fieldMap;
    }
}
