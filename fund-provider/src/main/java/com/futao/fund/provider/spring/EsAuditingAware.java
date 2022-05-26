package com.futao.fund.provider.spring;

import com.futao.fund.core.usercontext.CurrentUser;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 获取当前操作的用户
 * 泛型T为@CreatedBy or @LastModifiedBy的类型
 *
 * @author futaosmile@gmail.com
 * @date 2022/5/24
 */
@Component
public class EsAuditingAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(CurrentUser.s());
    }
}
