package com.futao.fund.spider.parsers;

import com.futao.fund.spider.annotations.processor.ColumnNameProcessor;
import com.futao.fund.spider.pageobject.PageObject;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableBody;
import com.gargoylesoftware.htmlunit.html.HtmlTableHeader;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import org.apache.commons.collections4.CollectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/19
 */
public class TableParser {
    private TableParser() {}

    /**
     * 读取标准table数据
     *
     * @param tableElement
     * @param type
     * @param <T>
     * @return
     */
    public static <T extends PageObject> List<T> parse(HtmlTable tableElement, Class<T> type) {
        List<HtmlTableBody> bodies = tableElement.getBodies();
        if (CollectionUtils.isEmpty(bodies)) {
            // body无数据，直接返回
            return new ArrayList<>(0);
        }
        // 获取到table header
        HtmlTableHeader header = tableElement.getHeader();
        // 获取标题行 row
        HtmlTableRow headerRow = header.getRows().get(0);
        // 标题包含的列数
        int headerRowColumnSize = headerRow.getCells().size();
        // 获取需要读取的标题对应的field
        Map<String, Field> columnFieldNameMap = ColumnNameProcessor.parse2Map(type);
        // 标题对应在爬取的table中的索引关系
        HashMap<Integer, Field> fieldIndexMap = new HashMap<>();
        // 设置标题对应在爬取的table中的索引关系
        for (int i = 0; i < headerRowColumnSize; i++) {
            String curColName = headerRow.getCell(i).asNormalizedText().trim();
            Field field = columnFieldNameMap.get(curColName);
            if (field != null) {
                fieldIndexMap.put(i, field);
            }
        }
        List<T> resultList = new ArrayList<>();
        for (HtmlTableBody body : bodies) {
            // 遍历每一行数据
            for (HtmlTableRow row : body.getRows()) {
                try {
                    T e = type.newInstance();
                    resultList.add(e);
                    for (int i = 0; i < row.getCells().size(); i++) {
                        // 读取到每个数据
                        Field field = fieldIndexMap.get(i);
                        // 判断当前这个数据是否需要被读取
                        if (field != null) {
                            field.setAccessible(true);
                            field.set(e, row.getCell(i).asNormalizedText().trim());
                        }
                    }
                } catch (InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return resultList;
    }
}
