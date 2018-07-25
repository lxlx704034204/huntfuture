package com.hante.domain.model;

import com.hante.common.domain.model.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 学校实体
 * @author fengqian
 * @since <pre>2018/07/24</pre>
 */
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "t_school")
public class School extends BaseModel {
    /**
     * 学校名称
     */
    @Column(name = "school_name", columnDefinition = "varchar(20)", nullable = false)
    private String name;

    /**
     * 学校备注
     */
    @Column(name = "remarks", columnDefinition = "varchar(20)")
    private String remarks;
}

   