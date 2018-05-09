package com.example.demo.common.enums;

import lombok.Getter;

@Getter
public enum ResultEnum implements BaseEnum {

    SUCCESS(0, "success"),
    FAIL(1, "fail");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
