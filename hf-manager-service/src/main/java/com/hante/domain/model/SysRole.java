package com.hante.domain.model;

import com.hante.common.domain.model.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 后台登录角色（控制权限用）
 */
@Setter
@Getter
@ToString(callSuper = true)
@Entity
@Table(name = "t_sys_role")
public class SysRole extends BaseModel {
    /**
     * 角色名
     */
    @Column(name = "role_name", columnDefinition = "char(10)", nullable = false)
    private String roleName;
}
