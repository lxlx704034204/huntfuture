package com.hante.domain.model;

import com.hante.domain.enums.EnterpriseNature;
import com.hante.domain.enums.EnterpriseScale;
import com.hante.domain.enums.Trade;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.hante.common.domain.model.BaseModel;

import javax.persistence.*;

/**
 * 企业信息表
 * @author fengqian
 * @since <pre>2018/07/24</pre>
 */
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "t_enterprise")
public class Enterprise extends BaseModel {
    /**
     * 企业名称
     */
    @Column(name = "name", columnDefinition = "varchar(20)", nullable = false)
    private String name;

    /**
     * 企业简介，富文本编辑器内容，使用text存储
     */
    @Column(name = "introduction", columnDefinition = "text", nullable = false)
    private String introduction;

    /**
     * 企业性质
     */
    @Column(name = "enterprise_nature", columnDefinition = "varchar(7)", nullable = false)
    @Enumerated(EnumType.STRING)
    private EnterpriseNature nature;

    /**
     * 企业规模
     */
    @Column(name = "enterprise_scale", columnDefinition = "varchar(10)", nullable = false)
    @Enumerated(EnumType.STRING)
    private EnterpriseScale scale;

    /**
     * 所属行业
     */
    @Column(name = "trade", columnDefinition = "varchar(20)", nullable = false)
    @Enumerated(EnumType.STRING)
    private Trade trade;

    /**
     * 企业banner图，url，此处长度不做限制
     */
    @Column(name = "banner", columnDefinition = "varchar(255)", nullable = false)
    private String banner;

}

   