package com.hante.common.domain.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

@Data
public class PageData<T> implements Serializable {
    private List<T> rows;
    private long total;

    public PageData(Page<T> page) {
        this.total = page.getTotalElements();
        this.rows = page.getContent();
    }
}
