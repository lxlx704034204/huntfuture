package com.hante.domain.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 后台角色-菜单中间表
 */
@Data
@Entity
@Table(name = "r_role_menu")
//注意物理删除的表不要继承BaseModel，也不要用@AutoQuery去执行查询，要自己写方法！
public class SysRoleMenuBO {
    /**
     * 主键（UUID）
     */
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    /**
     * 角色ID
     */
    @Column(name = "role_id", columnDefinition = "char(32)", nullable = false)
    private String roleId;

    /**
     * 菜单ID
     */
    @Column(name = "menu_id", columnDefinition = "char(32)", nullable = false)
    private String menuId;
}
