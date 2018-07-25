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
 * 学生倾向岗位实体
 * @author fengqian
 * @since <pre>2018/07/24</pre>
 */
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "t_tendency_position", indexes = {
        @Index(name = "student", columnList = "student_id")
})
public class TendencyPosition extends BaseModel {
    /**
     * 学生id
     */
    @Column(name = "student_id", columnDefinition = "char(32)", nullable = false)
    private String studentId;

    /**
     * 职位id
     */
    @Column(name = "position_id", columnDefinition = "char(32)", nullable = false)
    private String positionId;
}

   