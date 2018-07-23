package com.hante.common.domain.model;

import com.hante.common.domain.enums.EnumSysStatus;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseModel implements Serializable {
    /**
     * 主键（UUID）
     */
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    /**
     * 创建时间
     */
    @CreatedDate
    @Column(name = "create_at", nullable = false)
    private Date createAt;

    /**
     * 更新时间
     */
    @LastModifiedDate
    @Column(name = "update_at")
    private Date updateAt;

    /**
     * 删除标记字段
     */
    @Column(name = "sys_status", nullable = false)
    private Integer sysStatus = EnumSysStatus.NORMAL.getCode();
}
