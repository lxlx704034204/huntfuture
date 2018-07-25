package com.hante.domain.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 学生参与培养计划实体(学生档案信息)
 * @author fengqian
 * @since <pre>2018/07/24</pre>
 */
@Data
@Entity
@Table(name = "t_student_training", indexes = {
        @Index(name = "student", columnList = "student_id"),
        @Index(name = "training", columnList = "training_id")
})
public class StudentTraining {
    /**
     * 主键（UUID）
     */
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    /**
     * 学生账号id
     */
    @Column(name = "student_id", columnDefinition = "char(32)", nullable = false)
    private String studentId;

    /**
     * 提前培养计划id
     */
    @Column(name = "training_id", columnDefinition = "char(32)", nullable = false)
    private String trainingId;

    /**
     * 是否签到
     */
    @Column(name = "is_signed", columnDefinition = "bit(1)", nullable = false)
    private String signed;

}

   