package com.example.demo.common.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum {

    NOTPAYMENT(0,"success"),

    PAYMENT(1,"fail") ;

    private Integer code;
    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
