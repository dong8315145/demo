package com.example.demo.common.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum implements BaseEnum {

    NEW(0, "新建"),
    FINISHED(1, "完成"),
    CANCEL(2, "取消");

    private Integer code;
    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
