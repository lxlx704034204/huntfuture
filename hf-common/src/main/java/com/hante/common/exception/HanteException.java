package com.hante.common.exception;

public class HanteException extends RuntimeException {

    //错误的程序代码
    public static final Integer BAD_CODE = 999999;

    private Integer code;

    public HanteException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
