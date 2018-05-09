package com.example.demo.common.enums;

import lombok.Getter;

@Getter
public enum ExceptionEnum implements BaseEnum {
    NOT(0, "商品不存在");

    private Integer code;
    private String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
