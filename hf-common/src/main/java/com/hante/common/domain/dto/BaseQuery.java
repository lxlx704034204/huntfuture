package com.hante.common.domain.dto;

import lombok.Data;

/**
 * 基础查询类，可以配合bootstrap-table使用
 */
@Data
public class BaseQuery {
    /**
     * 当前第几页
     */
    private Integer pageNumber = 0;
    /**
     * 每页显示的条数
     */
    private Integer pageSize = 10;
}
