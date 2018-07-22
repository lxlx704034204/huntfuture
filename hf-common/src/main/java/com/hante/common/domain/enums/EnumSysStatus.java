package com.hante.common.domain.enums;

public enum EnumSysStatus {

    NORMAL(1, "正常"),
    DELETED(2, "标记删除");

    /**
     * code(数据库存储)
     */
    private Integer code;
    /**
     * 说明字段
     */
    private String label;

    EnumSysStatus(int code, String label){
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
}
