package com.hante.domain.model;

import com.hante.common.domain.model.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * 岗位实体
 * @author fengqian
 * @since <pre>2018/07/24</pre>
 */
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "t_position", indexes = {
        @Index(name = "company", columnList = "company_id")
})
public class Position extends BaseModel {
    /**
     * 岗位名称
     */
    @Column(name = "name", columnDefinition = "varchar(20)", nullable = false)
    private String name;

    /**
     * 所属公司id
     */
    @Column(name = "company_id", columnDefinition = "char(32)", nullable = false)
    private String companyId;

    /**
     * 薪资
     */
    @Column(name = "salary", columnDefinition = "varchar(20)", nullable = false)
    private String salary;

    /**
     * 岗位简介，富文本编辑器内容
     */
    @Column(name = "introduction", columnDefinition = "text", nullable = false)
    private String introduction;

    /**
     * 岗位福利
     */
     @Column(name = "welfare", columnDefinition = "varchar(100)", nullable = false)
     private String welfare;

    /**
     * 院系要求
     */
    @Column(name = "academy_requirement", columnDefinition = "varchar(20)", nullable = false)
    private String academyRequirement;

    /**
     * 能力要求
     */
    @Column(name = "ability_requirement", columnDefinition = "varchar(255)", nullable = false)
    private String abilityRequirement;

    /**
     * 其他要求
     */
    @Column(name = "other_requirement", columnDefinition = "varchar(255)", nullable = false)
    private String otherRequirement;

    /**
     * 岗位banner图
     */
    @Column(name = "banner", columnDefinition = "varchar(255)", nullable = false)
    private String banner;
     
}

   