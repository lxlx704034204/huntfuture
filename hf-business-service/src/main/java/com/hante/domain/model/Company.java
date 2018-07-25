package com.hante.domain.model;

import com.hante.domain.enums.City;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.hante.common.domain.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * 公司实体
 * @author fengqian
 * @since <pre>2018/07/24</pre>
 */
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "t_company", indexes = {
        @Index(name = "enterprise", columnList = "enterprise_id")
})
public class Company extends BaseModel {
    /**
     * 名称
     */
    @Column(name = "name", columnDefinition = "varchar(20)")
    private String name;

    /**
     * 公司所属企业id
     */
    @Column(name = "enterprise_id", columnDefinition = "char(32)", nullable = false)
    private String enterpriseId;

    /**
     * 简介，存储富文本编辑器内容
     */
    @Column(name = "introduction", columnDefinition = "text", nullable = false)
    private String introduction;

    /**
     * 公司所在城市
     */
    @Column(name = "city", columnDefinition = "varchar(6)", nullable = false)
    private City city;

    /**
     * 公司详细地址
     */
    @Column(name = "address", columnDefinition = "varchar(255)", nullable = false)
    private String address;

    /**
     * 公司banner图
     */
    @Column(name = "banner", columnDefinition = "varchar(255)", nullable = false)
    private String banner;
}

   