package com.example.demo.common.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum implements BaseEnum{
    NEW(0,"success"),

    FINISHEN(1,"fail") ;

    private Integer code;
    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
