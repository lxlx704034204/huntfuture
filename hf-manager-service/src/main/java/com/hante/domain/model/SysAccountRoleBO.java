package com.hante.domain.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 后台账号-角色中间表
 */
@Data
@Entity
@Table(name = "r_account_role")
//注意物理删除的表不要继承BaseModel，也不要用@AutoQuery去执行查询，要自己写方法！
public class SysAccountRoleBO {
    /**
     * 主键（UUID）
     */
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    /**
     * 账号ID
     */
    @Column(name = "account_id", columnDefinition = "char(32)", nullable = false)
    private String accountId;

    /**
     * 角色ID
     */
    @Column(name = "role_id", columnDefinition = "char(32)", nullable = false)
    private String roleId;
}
