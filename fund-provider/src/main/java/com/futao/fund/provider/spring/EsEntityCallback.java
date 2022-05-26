package com.futao.fund.provider.spring;

import com.alibaba.fastjson.JSON;
import com.futao.fund.provider.eso.AuditingBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.event.AfterSaveCallback;
import org.springframework.data.elasticsearch.core.event.BeforeConvertCallback;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Component;

/**
 * ES Entity Callbacks
 *
 * @author futaosmile@gmail.com
 * @date 2022/5/26
 */
@Slf4j
@Component
public class EsEntityCallback implements BeforeConvertCallback<AuditingBase>, AfterSaveCallback<AuditingBase> {
    @Override
    public AuditingBase onBeforeConvert(AuditingBase entity, IndexCoordinates index) {
        log.info("onBeforeConvert:{}", JSON.toJSONString(entity));
        return entity;
    }

    @Override
    public AuditingBase onAfterSave(AuditingBase entity, IndexCoordinates index) {
        log.info("onAfterSave:{}", JSON.toJSONString(entity));
        return entity;
    }
}

