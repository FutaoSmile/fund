package com.futao.fund.provider.eso;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.*;
import org.springframework.data.domain.Persistable;
import org.springframework.data.elasticsearch.annotations.*;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/24
 */
@Getter
@Setter
public abstract class AuditingBase implements Persistable<String> {
    public static final String ID = "_id";
    public static final String CREATE_BY = "createBy";
    public static final String CREATE_DATE_TIME = "createDateTime";
    public static final String UPDATE_BY = "updateBy";
    public static final String UPDATE_DATE_TIME = "updateDateTime";
    @Id
    private String id;
    @MultiField(
            mainField = @Field(type = FieldType.Text),
            otherFields = {
                    @InnerField(suffix = "keyword", type = FieldType.Keyword)
            }
    )
    @CreatedBy
    private String createBy;
    @Field(type = FieldType.Date, format = DateFormat.epoch_millis)
    @CreatedDate
    private Long createDateTime;
    @MultiField(
            mainField = @Field(type = FieldType.Text),
            otherFields = {
                    @InnerField(suffix = "keyword", type = FieldType.Keyword)
            }
    )
    @LastModifiedBy
    private String updateBy;
    @Field(type = FieldType.Date, format = DateFormat.epoch_millis)
    @LastModifiedDate
    private Long updateDateTime;

    /**
     * 用于ES审计功能
     * 如果修改时间为空则标记为新增数据，否则为修改数据
     *
     * @return 是否是新增数据
     */
    @Override
    public boolean isNew() {
        return id == null;
    }
}
