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
 * 后台账号登录主体
 */
@Setter
@Getter
@ToString(callSuper = true)
@Entity
//这里在数据库生成唯一索引的时候会报错，无视掉就行，可以正常使用，而且下次运行索引已存在的情况下就不会报错了
//注意，唯一索引的建立单纯是为了提高索引性能和当做数据库最后的一道屏障，插入或更新数据时仍应该先查再插
//这是因为：1.异常的处理很损耗性能；2：jpa在唯一索引违反的情况下抛出的是RunTimeException，不好处理
@Table(name = "t_sys_account", indexes = {
        @Index(name = "un_login_name", columnList = "login_name",unique = true)})
public class SysAccount extends BaseModel {
    /**
     * 登录名
     */
    @Column(name = "login_name", columnDefinition = "char(16)", nullable = false)
    private String loginName;

    /**
     * 登录密码（sha512加密）
     */
    @Column(name = "login_pwd", columnDefinition = "char(128)", nullable = false)
    private String loginPwd;

    /**
     * 安全手机（发送验证码等）
     */
    @Column(name = "safe_phone", columnDefinition = "char(11)", nullable = false)
    private String safePhone;

}
