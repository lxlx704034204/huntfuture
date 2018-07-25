package com.hante.common.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class ApiResponse implements Serializable {

    public static final int SUCCESS_CODE = 0;
    private int code = SUCCESS_CODE;
    private String msg = "成功";
    private Object data;

    @JsonIgnore
    private Object reason;

    public ApiResponse() {
    }

    public ApiResponse(int code) {
        this.code = code;
    }

    public ApiResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ApiResponse(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
