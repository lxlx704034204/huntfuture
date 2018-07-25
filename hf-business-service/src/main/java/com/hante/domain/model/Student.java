package com.hante.domain.model;

import com.hante.common.domain.model.BaseModel;
import com.hante.domain.enums.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 学生账号实体
 * @author fengqian
 * @since <pre>2018/07/24</pre>
 */
@Getter
@Setter
@ToString(callSuper = true)
@Entity
//这里在数据库生成唯一索引的时候会报错，无视掉就行，可以正常使用，而且下次运行索引已存在的情况下就不会报错了
//注意，唯一索引的建立单纯是为了提高索引性能和当做数据库最后的一道屏障，插入或更新数据时仍应该先查再插
//这是因为：1.异常的处理很损耗性能；2：jpa在唯一索引违反的情况下抛出的是RunTimeException，不好处理
@Table(name = "t_student", indexes = {
        @Index(name = "username", columnList = "username", unique = true),
        @Index(name = "school", columnList = "school_id")
})
public class Student extends BaseModel{
    // 以下为必填项
    /**
     * 账号名
     */
    @Column(name = "username", columnDefinition = "char(16)", nullable = false)
    private String username;

    /**
     * 登录密码（sha512加密）
     */
    @Column(name = "login_pwd", columnDefinition = "char(128)", nullable = false)
    private String loginPwd;

    /**
     * 姓名
     */
    @Column(name = "full_name", columnDefinition = "varchar(16)", nullable = false)
    private String fullName;

    /**
     * 手机号
     */
    @Column(name = "mobile_phone", columnDefinition = "char(11)", nullable = false)
    private String mobilePhone;

    /**
     * 学校id
     */
    @Column(name = "school_id", columnDefinition = "char(32)", nullable = false)
    private String schoolId;

    /**
     * 学院id
     */
    @Column(name = "academy_id", columnDefinition = "char(32)", nullable = false)
    private String academyId;

    /**
     * 专业
     */
    @Column(name = "major", columnDefinition = "varchar(16)", nullable = false)
    private String major;

    /**
     * 毕业时间
     */
    @Column(name = "graduation_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy年MM月")
    private Date graduationDate;

    // 以下为选填项
    /**
     * qq号，目前位数5-11
     */
    @Column(name = "qq", columnDefinition = "varchar(12)")
    private String qq;

    /**
     * 微信号
     */
    @Column(name = "wechat", columnDefinition = "varchar(20)")
    private String wechat;

    /**
     * 性别
     */
    @Column(name = "gender", columnDefinition = "char(1)")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    /**
     * 籍贯
     */
    @Column(name = "native_address", columnDefinition = "varchar(20)")
    private String nativeAddress;

}
