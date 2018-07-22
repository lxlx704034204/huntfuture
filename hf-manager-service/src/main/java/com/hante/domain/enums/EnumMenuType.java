package com.hante.domain.enums;

/**
 * 菜单父模块枚举类
 */
public enum EnumMenuType {
    STUDENT(0, "学生信息管理"),
    COMPANY(1, "企业信息管理"),
    RESUME(2, "简历信息管理"),
    RANKING(3, "榜单信息管理"),
    MANAGER(4, "后台管理"),
    OPERATION(5, "运维管理");
    /**
     * code(数据库存储)
     */
    private Integer code;
    /**
     * 说明字段
     */
    private String label;

    EnumMenuType(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public static EnumMenuType valueOfCode(Integer code) {
        for (EnumMenuType menuType : EnumMenuType.values()) {
            if (menuType.getCode().intValue() == code.intValue()) {
                return menuType;
            }
        }
        return null;
    }
}
