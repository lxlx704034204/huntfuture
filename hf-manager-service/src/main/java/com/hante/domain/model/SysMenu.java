package com.hante.domain.model;


import com.hante.common.domain.model.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 后台菜单模块（控制权限用）
 */
@Setter
@Getter
@ToString(callSuper = true)
@Entity
@Table(name = "t_sys_menu")
public class SysMenu extends BaseModel {
    /**
     * 模块名称
     */
    @Column(name = "menu_name", columnDefinition = "varchar(20)", nullable = false)
    private String menuName;

    /**
     * 模块类型
     * @see com.hante.domain.enums.EnumMenuType
     */
    @Column(name = "menu_type", nullable = false)
    private Integer menuType;

    /**
     * 接口地址起始部分
     */
    @Column(name = "url", columnDefinition = "varchar(30)", nullable = false)
    private String url;
}
