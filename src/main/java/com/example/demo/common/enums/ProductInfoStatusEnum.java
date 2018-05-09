package com.example.demo.common.enums;

import lombok.Getter;

/**
 * 商品状态
 */
@Getter
public enum ProductInfoStatusEnum implements BaseEnum {

    UP(0, "上架"), DOWN(1, "下架");
    private Integer code;
    private String message;

    ProductInfoStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
