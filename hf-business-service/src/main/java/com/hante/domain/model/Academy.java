package com.hante.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.hante.common.domain.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 学院实体
 * @author fengqian
 * @since <pre>2018/07/24</pre>
 */
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "t_academy")
public class Academy extends BaseModel {
    /**
     * 学院名称
     */
    @Column(name = "academy_name", columnDefinition = "varchar(20)", nullable = false)
    private String name;

    /**
     * 所属学校id
     */
    @Column(name = "school_id", columnDefinition = "char(32)", nullable = false)
    private String schoolId;
}

   