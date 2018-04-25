package com.example.demo.View.BaseView;

import lombok.Data;

/**
 * 返回的最基本对像
 */
@Data
public class BaseVO<T> {
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 错误信息
     */
    private String msg;
    /**
     * 具体数据
     */
    private T data;
}
