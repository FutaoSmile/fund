package com.futao.fund.provider.spring;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * 获取当前操作的用户
 * 泛型T为@CreatedBy or @LastModifiedBy的类型
 *
 * @author futaosmile@gmail.com
 * @date 2022/5/24
 */
public class AuditingAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.empty();
    }
}
