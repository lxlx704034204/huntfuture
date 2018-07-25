package com.hante.domain.model;

import com.hante.common.domain.model.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.util.Date;

/**
 * 公司提前培养计划
 * @author fengqian
 * @since <pre>2018/07/24</pre>
 */
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "t_company_training", indexes = {
        @Index(name = "company", columnList = "company_id")
})
public class ComTraining extends BaseModel {

    // 活动是否结束由截止时间决定

    /**
     * 活动名称
     */
    @Column(name = "name", columnDefinition = "varchar(20)", nullable = false)
    private String name;

    /**
     * 所属公司id
     */
    @Column(name = "company_id", columnDefinition = "char(32)", nullable = false)
    private String companyId;

    /**
     * 活动开始时间
     */
    @Column(name = "begin_time", nullable = false)
    @DateTimeFormat(pattern = "yyyy年MM月dd日HH时MM分")
    private Date beginTime;

    /**
     * 活动结束时间
     */
    @Column(name = "end_time",  nullable = false)
    @DateTimeFormat(pattern = "yyyy年MM月dd日HH时MM分")
    private String end_time;

    /**
     * 活动地点
     */
    @Column(name = "address", columnDefinition = "varchar(255)", nullable = false)
    private String address;

    /**
     * 活动二维码
     */
    @Column(name = "qr_code", columnDefinition = "varchar(255)", nullable = false)
    private String qrCode;

}

   