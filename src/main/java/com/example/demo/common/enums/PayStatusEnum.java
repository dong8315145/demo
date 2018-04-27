package com.example.demo.common.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum {
    NOTPAYMENT(0, "未付款"),
    PAYMENT(1, "已付款"),
    APPLY(2, "申请退款"),
    ADOPT(3, "通过退款"),
    REFUND(4, "已退款");

    private Integer code;
    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
