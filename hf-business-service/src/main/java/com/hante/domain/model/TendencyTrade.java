package com.hante.domain.model;

import com.hante.common.domain.model.BaseModel;
import com.hante.domain.enums.Trade;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * 学生倾向行业实体
 * @author fengqian
 * @since <pre>2018/07/25</pre>
 */
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "t_tendency_trade", indexes = {
        @Index(name = "student", columnList = "student_id")
})
public class TendencyTrade extends BaseModel{
    /**
     * 学生id
     */
    @Column(name = "student_id", columnDefinition = "char(32)", nullable = false)
    private String studentId;

    /**
     * 行业
     */
    @Column(name = "trade", columnDefinition = "varchar(20)", nullable = false)
    private Trade trade;
}

   