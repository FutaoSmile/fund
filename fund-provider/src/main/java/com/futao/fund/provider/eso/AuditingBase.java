package com.futao.fund.provider.eso;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/24
 */
@Getter
@Setter
public abstract class AuditingBase implements Persistable<String> {

    @CreatedBy
    private String createBy;

    @CreatedDate
    private Long createDateTime;

    @LastModifiedBy
    private String updateBy;

    @LastModifiedDate
    private Long updateDateTime;

}
